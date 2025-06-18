package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public class FixedAmountPriceAdjustmentStrategy implements PriceAdjustmentStrategy {
    @Override
    public BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal value) {
        return totalPrice.subtract(value).max(BigDecimal.ZERO);
    }
}
