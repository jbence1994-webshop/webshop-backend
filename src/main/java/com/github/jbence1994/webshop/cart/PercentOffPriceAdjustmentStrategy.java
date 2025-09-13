package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffPriceAdjustmentStrategy implements PriceAdjustmentStrategy {

    @Override
    public Price adjustPrice(BigDecimal totalPrice, BigDecimal discountValue) {
        var discountAmount = totalPrice
                .multiply(discountValue)
                .setScale(2, RoundingMode.HALF_UP);

        var discountedTotalPrice = totalPrice
                .subtract(discountAmount)
                .max(BigDecimal.ZERO);

        return Price.withShippingCost(discountedTotalPrice, discountAmount);
    }
}
