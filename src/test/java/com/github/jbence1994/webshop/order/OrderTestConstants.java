package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderTestConstants {
    BigDecimal TOTAL_PRICE = BigDecimal.valueOf(49.99);
    BigDecimal UNIT_PRICE = TOTAL_PRICE;
    LocalDateTime CREATED_AT = LocalDateTime.of(2025, 6, 18, 16, 18, 59);
}
