package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.CartService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.order.Order;
import com.github.jbence1994.webshop.order.OrderService;
import com.github.jbence1994.webshop.order.PaymentStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    private final CartQueryService cartQueryService;
    private final CouponService couponService;
    private final OrderService orderService;
    private final CartService cartService;
    private final AuthService authService;

    @Override
    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cartId = request.getCartId();

        var cart = cartQueryService.getCart(cartId);

        if (cart.isEmpty()) {
            throw new EmptyCartException(cartId);
        }

        var user = authService.getCurrentUser();

        var order = Order.fromCart(cart, user);
        orderService.createOrder(order);

        if (cart.hasCouponApplied()) {
            couponService.redeemCoupon(user.getId(), cart.getAppliedCoupon().getCode());
        }

        cartService.clearCart(cartId);

        // TODO: Algorithm to increase loyalty and reward points.

        // TODO: Payment integration.

        // 1) If payment was successful:
        order.setStatus(PaymentStatus.COMPLETED);

        //2) If payment was failed:
        // order.setStatus(PaymentStatus.FAILED);

        // 3) If payment aborted for a long time:
        // order.setStatus(PaymentStatus.CANCELLED);

        return new CheckoutResponse(order.getId());
    }
}
