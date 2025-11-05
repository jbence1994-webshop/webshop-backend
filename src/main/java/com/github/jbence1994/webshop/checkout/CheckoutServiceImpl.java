package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.order.OrderQueryService;
import com.github.jbence1994.webshop.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final CheckoutQueryService checkoutQueryService;
    private final CheckoutRepository checkoutRepository;
    private final CouponQueryService couponQueryService;
    private final OrderQueryService orderQueryService;
    private final CartQueryService cartQueryService;
    private final LoyaltyService loyaltyService;
    private final PaymentGateway paymentGateway;
    private final CouponService couponService;
    private final OrderService orderService;
    private final AuthService authService;

    @Override
    public CheckoutSession createCheckoutSession(UUID cartId) {
        var cart = cartQueryService.getCart(cartId);

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var checkoutSession = CheckoutSession.from(cart);

        save(checkoutSession);

        return checkoutSession;
    }

    @Override
    public CheckoutSession applyCouponToCheckoutSession(UUID id, String couponCode) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(id);

        if (checkoutSession.isExpired()) {
            checkoutSession.setStatus(CheckoutStatus.EXPIRED);
            throw new ExpiredCheckoutSessionException(id);
        }

        var coupon = couponQueryService.getCoupon(couponCode);

        if (coupon.isExpired()) {
            throw new ExpiredCouponException(couponCode);
        }

        if (couponQueryService.isCouponRedeemed(couponCode)) {
            throw new CouponAlreadyRedeemedException(couponCode);
        }

        checkoutSession.applyCoupon(coupon);

        save(checkoutSession);

        return checkoutSession;
    }

    @Override
    public CheckoutSession removeCouponFromCheckoutSession(UUID id) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(id);

        if (checkoutSession.isExpired()) {
            checkoutSession.setStatus(CheckoutStatus.EXPIRED);
            throw new ExpiredCheckoutSessionException(id);
        }

        checkoutSession.removeCoupon();

        save(checkoutSession);

        return checkoutSession;
    }

    @Override
    @Transactional
    public CheckoutSession completeCheckoutSession(UUID checkoutSessionId) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(checkoutSessionId);

        if (checkoutSession.isExpired()) {
            checkoutSession.setStatus(CheckoutStatus.EXPIRED);
            throw new ExpiredCheckoutSessionException(checkoutSessionId);
        }

        if (CheckoutStatus.COMPLETED.equals(checkoutSession.getStatus())) {
            throw new CheckoutSessionAlreadyCompletedException(checkoutSessionId);
        }

        var cart = checkoutSession.getCart();
        var cartId = cart.getId();

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var user = authService.getCurrentUser();

        var order = Order.from(user, checkoutSession);

        orderService.createOrder(order);

        checkoutSession.getAppliedCoupon()
                .ifPresent(coupon -> couponService.redeemCoupon(user.getId(), coupon.getCode(), order.getId()));

        var loyaltyPoints = loyaltyService.calculateLoyaltyPoints(order.getTotalPrice());
        user.earnLoyaltyPoints(loyaltyPoints);
        order.setLoyaltyPoints(loyaltyPoints);

        try {
            var paymentSessionRequest = new PaymentSessionRequest(checkoutSession, order);

            var paymentSessionResponse = paymentGateway.createPaymentSession(paymentSessionRequest);

            cart.clear();

            checkoutSession.setOrder(order);
            checkoutSession.setCheckoutUrl(paymentSessionResponse.checkoutUrl());

            return checkoutSession;
        } catch (PaymentException exception) {
            orderService.deleteOrder(order.getId());
            throw exception;
        }
    }

    @Override
    public void handleCompleteCheckoutSessionWebhookEvent(WebhookRequest request) {
        paymentGateway
                .parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    var order = orderQueryService.getOrder(paymentResult.orderId());
                    order.setStatus(paymentResult.orderStatus());
                    orderService.updateOrder(order);

                    var checkoutSession = checkoutQueryService.getCheckoutSession(paymentResult.checkoutSessionId());
                    checkoutSession.setStatus(paymentResult.checkoutStatus());
                    save(checkoutSession);
                });
    }

    private void save(CheckoutSession checkoutSession) {
        checkoutRepository.save(checkoutSession);
    }
}
