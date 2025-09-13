package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FixedAmountCheckoutPriceAdjustmentStrategy implements CheckoutPriceAdjustmentStrategy {

    @Override
    public CheckoutPrice adjustCheckoutPrice(BigDecimal cartTotal, BigDecimal discountValue) {
        var discountedTotalPrice = cartTotal
                .subtract(discountValue)
                .max(BigDecimal.ZERO);

        discountedTotalPrice = discountedTotalPrice.setScale(2, RoundingMode.HALF_UP);

        var normalizedDiscountValue = discountValue.setScale(2, RoundingMode.HALF_UP);

        return CheckoutPrice.withShippingCost(discountedTotalPrice, normalizedDiscountValue);
    }
}
