package com.github.jbence1994.webshop.coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponDto(
        String code,
        String type,
        BigDecimal value,
        String description,
        LocalDateTime expirationDate
) {
}
