package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderStatus;

public record PaymentResult(
        Long orderId,
        OrderStatus status
) {
}
