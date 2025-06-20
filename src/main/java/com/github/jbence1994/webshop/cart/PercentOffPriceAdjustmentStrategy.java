package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffPriceAdjustmentStrategy implements PriceAdjustmentStrategy {

    @Override
    public BigDecimal adjustPrice(BigDecimal totalPrice, BigDecimal value) {
        var discountValue = totalPrice
                .multiply(value)
                .setScale(2, RoundingMode.UP);

        return totalPrice.subtract(discountValue);
    }
}
