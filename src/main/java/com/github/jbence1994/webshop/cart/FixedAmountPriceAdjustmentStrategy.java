package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FixedAmountPriceAdjustmentStrategy implements PriceAdjustmentStrategy {

    @Override
    public Price adjustPrice(BigDecimal totalPrice, BigDecimal discountValue) {
        var discountedTotalPrice = totalPrice
                .subtract(discountValue)
                .max(BigDecimal.ZERO);

        discountedTotalPrice = discountedTotalPrice.setScale(2, RoundingMode.HALF_UP);

        var normalizedDiscountValue = discountValue.setScale(2, RoundingMode.HALF_UP);

        return Price.withShippingCost(discountedTotalPrice, normalizedDiscountValue);
    }
}
