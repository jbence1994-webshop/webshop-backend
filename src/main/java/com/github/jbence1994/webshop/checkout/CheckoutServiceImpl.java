package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.loyalty.LoyaltyPointsCalculator;
import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.order.OrderService;
import com.github.jbence1994.webshop.order.OrderStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final LoyaltyPointsCalculator loyaltyPointsCalculator;
    private final CheckoutQueryService checkoutQueryService;
    private final CheckoutRepository checkoutRepository;
    private final CouponQueryService couponQueryService;
    private final CartQueryService cartQueryService;
    private final CouponService couponService;
    private final OrderService orderService;
    private final AuthService authService;

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

        if (cart.isEmpty()) {
            throw new EmptyCartException(cart.getId());
        }

        var user = authService.getCurrentUser();

        var order = Order.from(user, checkoutSession, cart);

        orderService.createOrder(order);

        if (checkoutSession.hasCouponApplied()) {
            couponService.redeemCoupon(
                    user.getId(),
                    checkoutSession.getCouponCode(),
                    order.getId()
            );
        }

        var earnedLoyaltyPoints = loyaltyPointsCalculator.calculateLoyaltyPoints(order.getTotalPrice());
        user.earnLoyaltyPoints(earnedLoyaltyPoints);
        order.setLoyaltyPoints(earnedLoyaltyPoints);

        // TODO: Payment integration.

        // TODO: 1) If payment was successful:
        cart.clear();

        order.setStatus(OrderStatus.CONFIRMED);
        checkoutSession.setStatus(CheckoutStatus.COMPLETED);
        // TODO: Create on-stock schema to store products on stock, here: decrease available quantity.

        // TODO: 2) If payment was failed:
        // TODO: order.setStatus(OrderStatus.CREATED);
        // TODO: CheckoutStatus.FAILED;

        // TODO: 3) If payment aborted for a long time:
        // TODO: order.setStatus(OrderStatus.CANCELLED);
        // TODO: CheckoutStatus.CANCELLED;

        return new CompleteCheckoutSessionResponse(order.getId());
    }
}
