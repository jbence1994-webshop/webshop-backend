package com.github.jbence1994.webshop.checkout;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FixedAmountCartTotalAdjustmentStrategy implements CartTotalAdjustmentStrategy {

    @Override
    public Pair<BigDecimal, BigDecimal> adjustCartTotal(BigDecimal cartTotal, BigDecimal discountValue) {
        var discountedCartTotal = cartTotal
                .subtract(discountValue)
                .max(BigDecimal.ZERO);

        discountedCartTotal = discountedCartTotal.setScale(2, RoundingMode.HALF_UP);

        var normalizedDiscountValue = discountValue.setScale(2, RoundingMode.HALF_UP);

        return Pair.of(discountedCartTotal, normalizedDiscountValue);
    }
}
