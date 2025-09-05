package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.order.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CheckoutSessionRequest(
        UUID cartId,
        Long orderId,
        List<OrderItem> items,
        BigDecimal totalPrice,
        BigDecimal discountAmount,
        Coupon appliedCoupon
) {
}
