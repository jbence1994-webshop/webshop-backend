package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public record Price(BigDecimal totalPrice, BigDecimal discountAmount, BigDecimal shippingCost) {
    public static Price withShippingCost(BigDecimal totalPrice) {
        return new Price(totalPrice, BigDecimal.ZERO, BigDecimal.valueOf(20.00));
    }

    public static Price withShippingCost(BigDecimal totalPrice, BigDecimal discountAmount) {
        return new Price(totalPrice, discountAmount, BigDecimal.valueOf(20.00));
    }

    public static Price withFreeShipping(BigDecimal totalPrice) {
        return new Price(totalPrice, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
