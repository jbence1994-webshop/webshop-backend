package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public record CheckoutSessionRequest(
        Long orderId,
        List<OrderItem> items,
        BigDecimal totalPrice,
        BigDecimal discountAmount,
        BigDecimal shippingCost,
        String couponCode
) {
}
