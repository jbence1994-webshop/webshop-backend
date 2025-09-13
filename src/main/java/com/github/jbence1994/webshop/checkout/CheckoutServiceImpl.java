package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.CartService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.loyalty.LoyaltyPointsCalculator;
import com.github.jbence1994.webshop.order.Order;
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
    private final CheckoutQueryService checkoutQueryService;
    private final CheckoutRepository checkoutRepository;
    private final CouponQueryService couponQueryService;
    private final OrderQueryService orderQueryService;
    private final CartQueryService cartQueryService;
    private final PaymentGateway paymentGateway;
    private final CouponService couponService;
    private final OrderService orderService;
    private final AuthService authService;
    private final CartService cartService;

    @Override
    public CheckoutSession createCheckoutSession(UUID cartId) {
        var cart = cartQueryService.getCart(cartId);

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var checkoutSession = new CheckoutSession();
        checkoutSession.setCart(cart);
        checkoutSession.setStatus(CheckoutStatus.PENDING);

        checkoutRepository.save(checkoutSession);

        return checkoutSession;
    }

    @Override
    public CheckoutSession applyCouponToCheckoutSession(UUID id, String couponCode) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(id);
        var coupon = couponQueryService.getCoupon(couponCode);

        if (coupon.isExpired()) {
            throw new ExpiredCouponException(couponCode);
        }

        if (couponQueryService.isRedeemedCoupon(couponCode)) {
            throw new CouponAlreadyRedeemedException(couponCode);
        }

        checkoutSession.setAppliedCoupon(coupon);

        checkoutRepository.save(checkoutSession);

        return checkoutSession;
    }

    @Override
    public CheckoutSession removeCouponFromCheckoutSession(UUID id) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(id);

        checkoutSession.setAppliedCoupon(null);

        checkoutRepository.save(checkoutSession);

        return checkoutSession;
    }

    @Override
    @Transactional
    public CompleteCheckoutSessionResponse completeCheckoutSession(UUID checkoutSessionId) {
        var checkoutSession = checkoutQueryService.getCheckoutSession(checkoutSessionId);

        if (CheckoutStatus.COMPLETED.equals(checkoutSession.getStatus())) {
            throw new CheckoutSessionAlreadyCompletedException(checkoutSessionId);
        }

        var cart = checkoutSession.getCart();
        var cartId = cart.getId();

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var user = authService.getCurrentUser();

        var cartTotal = cart.calculateTotal();

        CheckoutPrice checkoutPrice;

        if (!checkoutSession.hasCouponApplied()) {
            checkoutPrice = CheckoutPrice.of(cartTotal, BigDecimal.ZERO);
        } else {
            var appliedCoupon = checkoutSession.getAppliedCoupon();

            checkoutPrice = CheckoutPriceAdjustmentStrategyFactory
                    .getCheckoutPriceAdjustmentStrategy(appliedCoupon.getType())
                    .adjustCheckoutPrice(cartTotal, appliedCoupon.getValue());
        }

        var order = Order.from(user, checkoutPrice, cart);

        orderService.createOrder(order);

        if (checkoutSession.hasCouponApplied()) {
            couponService.redeemCoupon(
                    user.getId(),
                    checkoutSession.getCouponCode(),
                    order.getId()
            );
        }

        var loyaltyPoints = loyaltyPointsCalculator.calculateLoyaltyPoints(order.getTotalPrice());
        user.earnLoyaltyPoints(loyaltyPoints);
        order.setLoyaltyPoints(loyaltyPoints);

        try {
            var paymentSessionRequest = new PaymentSessionRequest(
                    cartId,
                    order,
                    checkoutSessionId,
                    checkoutSession.hasCouponApplied() ? checkoutSession.getAppliedCoupon() : null
            );

            var paymentSessionResponse = paymentGateway.createPaymentSession(paymentSessionRequest);

            cart.clear();

            return new CompleteCheckoutSessionResponse(
                    order.getId(),
                    paymentSessionResponse.checkoutUrl()
            );
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

                    var cart = cartQueryService.getCart(paymentResult.cartId());
                    cartService.deleteCart(cart.getId());
                });
    }
}
