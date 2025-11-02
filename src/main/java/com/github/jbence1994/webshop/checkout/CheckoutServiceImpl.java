package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.order.OrderPricing;
import com.github.jbence1994.webshop.order.OrderQueryService;
import com.github.jbence1994.webshop.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final LoyaltyPointsCalculator loyaltyPointsCalculator;
    private final RewardPointsConverter rewardPointsConverter;
    private final CheckoutQueryService checkoutQueryService;
    private final CheckoutRepository checkoutRepository;
    private final CouponQueryService couponQueryService;
    private final OrderQueryService orderQueryService;
    private final CartQueryService cartQueryService;
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

        checkoutRepository.save(checkoutSession);

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

        checkoutRepository.save(checkoutSession);

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

        checkoutRepository.save(checkoutSession);

        return checkoutSession;
    }

    @Override
    @Transactional
    public CheckoutSession completeCheckoutSession(UUID checkoutSessionId, RewardPointsAction action) {
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

        var cartTotal = checkoutSession.getCartTotal();

        var orderPricing = OrderPricing.of();

        if (RewardPointsAction.EARN.equals(action)) {
            var earnedRewardPoints = rewardPointsConverter.toRewardPoints(cartTotal, user.getMembershipTier());

            user.earnRewardPoints(earnedRewardPoints);

            orderPricing = OrderPricing.of(cartTotal, cartTotal, BigDecimal.ZERO);
        }
        // TODO: Implement 'BURN' path in else branch.

        var order = Order.from(user, checkoutSession, orderPricing);

        orderService.createOrder(order);

        checkoutSession.getAppliedCoupon()
                .ifPresent(coupon -> couponService.redeemCoupon(user.getId(), coupon.getCode(), order.getId()));

        var loyaltyPoints = loyaltyPointsCalculator.calculateLoyaltyPoints(order.getTotalPrice());
        user.earnLoyaltyPoints(loyaltyPoints);
        order.setEarnedLoyaltyPoints(loyaltyPoints);

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
                    checkoutRepository.save(checkoutSession);
                });
    }
}
