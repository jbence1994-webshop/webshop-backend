package com.github.jbence1994.webshop.checkout;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckoutSessionDto(
        UUID id,
        UUID cartId,
        String appliedCoupon,
        String status,
        LocalDateTime createdAt
) {
}
