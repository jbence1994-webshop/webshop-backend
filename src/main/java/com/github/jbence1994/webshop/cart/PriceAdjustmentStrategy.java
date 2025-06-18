package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public interface PriceAdjustmentStrategy {
    BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal value);
}
