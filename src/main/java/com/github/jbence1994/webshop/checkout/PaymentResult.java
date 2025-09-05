package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderStatus;

import java.util.UUID;

public record PaymentResult(
        UUID cartId,
        Long orderId,
        OrderStatus status
) {
}
