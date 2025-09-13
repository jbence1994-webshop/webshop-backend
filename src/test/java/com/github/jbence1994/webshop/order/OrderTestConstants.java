package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderTestConstants {
    BigDecimal DISCOUNT_AMOUNT = BigDecimal.ZERO;
    int EARNED_LOYALTY_POINTS = 2;
    LocalDateTime CREATED_AT = LocalDateTime.of(2025, 9, 13, 11, 20, 11);
}
