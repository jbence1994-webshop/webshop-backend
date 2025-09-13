package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.order.Order;

import java.util.UUID;

public record PaymentSessionRequest(
        UUID cartId,
        Order order,
        UUID checkoutSessionId,
        Coupon appliedCoupon
) {
}
