package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public class FreeShippingPriceAdjustmentStrategy implements PriceAdjustmentStrategy {

    @Override
    public Price adjustPrice(BigDecimal totalPrice, BigDecimal discountValue) {
        return Price.withFreeShipping(totalPrice, discountValue);
    }
}
