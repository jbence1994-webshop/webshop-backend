package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CheckoutSessionDto(
        UUID id,
        UUID cartId,
        BigDecimal cartTotal,
        BigDecimal discountAmount,
        String appliedCoupon,
        String status,
        LocalDateTime createdAt
) {
}
