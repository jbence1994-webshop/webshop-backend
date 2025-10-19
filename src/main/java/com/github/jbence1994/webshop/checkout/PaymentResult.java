package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.order.OrderStatus;

import java.util.UUID;

public record PaymentResult(
        String eventType,
        UUID cartId,
        Long orderId,
        UUID checkoutSessionId,
        OrderStatus orderStatus,
        CheckoutStatus checkoutStatus
) {
}
