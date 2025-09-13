package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

public interface CheckoutPriceAdjustmentStrategy {
    CheckoutPrice adjustCheckoutPrice(
            BigDecimal cartTotal,
            BigDecimal discountValue,
            BigDecimal shippingCost
    );
}
