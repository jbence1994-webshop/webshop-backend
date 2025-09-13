package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOffCheckoutPriceAdjustmentStrategy implements CheckoutPriceAdjustmentStrategy {

    @Override
    public CheckoutPrice adjustCheckoutPrice(BigDecimal cartTotal, BigDecimal discountValue) {
        var discountAmount = cartTotal
                .multiply(discountValue)
                .setScale(2, RoundingMode.HALF_UP);

        var discountedCartTotal = cartTotal
                .subtract(discountAmount)
                .max(BigDecimal.ZERO);

        return CheckoutPrice.withShippingCost(discountedCartTotal, discountAmount);
    }
}
