package com.github.jbence1994.webshop.checkout;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffCartTotalAdjustmentStrategy implements CartTotalAdjustmentStrategy {

    @Override
    public Pair<BigDecimal, BigDecimal> adjustCartTotal(BigDecimal cartTotal, BigDecimal discountValue) {
        var discountAmount = cartTotal
                .multiply(discountValue)
                .setScale(2, RoundingMode.HALF_UP);

        var discountedCartTotal = cartTotal
                .subtract(discountAmount)
                .max(BigDecimal.ZERO);

        return Pair.of(discountedCartTotal, discountAmount);
    }
}
