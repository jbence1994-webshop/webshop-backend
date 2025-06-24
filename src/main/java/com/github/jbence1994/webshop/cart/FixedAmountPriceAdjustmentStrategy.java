package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public class FixedAmountPriceAdjustmentStrategy implements PriceAdjustmentStrategy {

    @Override
    public Price adjustPrice(BigDecimal totalPrice, BigDecimal discountValue) {
        var discountedTotalPrice = totalPrice
                .subtract(discountValue)
                .max(BigDecimal.ZERO);

        return Price.withDefaultShipping(discountedTotalPrice, discountValue);
    }
}
