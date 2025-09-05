package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.CartService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.loyalty.LoyaltyPointsCalculator;
import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.order.OrderQueryService;
import com.github.jbence1994.webshop.order.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final LoyaltyPointsCalculator loyaltyPointsCalculator;
    private final OrderQueryService orderQueryService;
    private final CartQueryService cartQueryService;
    private final PaymentGateway paymentGateway;
    private final CouponService couponService;
    private final OrderService orderService;
    private final AuthService authService;
    private final CartService cartService;

    @Override
    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cartId = request.getCartId();

        var cart = cartQueryService.getCart(cartId);

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var user = authService.getCurrentUser();

        var order = Order.from(cart);
        order.setCustomer(user);
        orderService.createOrder(order);

        if (cart.hasCouponApplied()) {
            couponService.redeemCoupon(
                    user.getId(),
                    cart.getCouponCode(),
                    order.getId()
            );
        }

        var earnedLoyaltyPoints = loyaltyPointsCalculator.calculateLoyaltyPoints(order.getTotalPrice());
        user.earnLoyaltyPoints(earnedLoyaltyPoints);
        order.setLoyaltyPoints(earnedLoyaltyPoints);

        try {
            var checkoutSessionRequest = new CheckoutSessionRequest(
                    cartId,
                    order.getId(),
                    order.getItems(),
                    order.getTotalPrice(),
                    order.getDiscountAmount(),
                    cart.hasCouponApplied() ? cart.getAppliedCoupon() : null
            );

            var checkoutSessionResponse = paymentGateway.createCheckoutSession(checkoutSessionRequest);

            cart.clear();

            return new CheckoutResponse(
                    order.getId(),
                    checkoutSessionResponse.checkoutUrl()
            );
        } catch (PaymentException exception) {
            orderService.deleteOrder(order);
            throw exception;
        }
    }

    @Override
    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway
                .parseWebhookRequest(request)
                .ifPresent(paymentResult -> {
                    var order = orderQueryService.getOrder(paymentResult.orderId());
                    order.setStatus(paymentResult.status());
                    orderService.updateOrder(order);
                    var cart = cartQueryService.getCart(paymentResult.cartId());
                    cartService.deleteCart(cart.getId());
                });
    }
}
