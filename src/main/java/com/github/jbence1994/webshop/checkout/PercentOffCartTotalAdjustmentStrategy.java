package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffCartTotalAdjustmentStrategy implements CartTotalAdjustmentStrategy {

    @Override
    public AdjustedCartTotal adjustCartTotal(BigDecimal cartTotal, BigDecimal discountValue) {
        var discountAmount = cartTotal
                .multiply(discountValue)
                .setScale(2, RoundingMode.HALF_UP);

        var discountedCartTotal = cartTotal
                .subtract(discountAmount)
                .max(BigDecimal.ZERO);

        return AdjustedCartTotal.of(discountedCartTotal, discountAmount);
    }
}
