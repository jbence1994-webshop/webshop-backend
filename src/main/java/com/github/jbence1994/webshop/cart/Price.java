package com.github.jbence1994.webshop.cart;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Price {
    private BigDecimal totalPrice;
    private BigDecimal discountAmount;
    private BigDecimal shippingCost;

    private static final BigDecimal DEFAULT_SHIPPING_COST = BigDecimal.valueOf(20.00);

    public static Price withShippingCost(BigDecimal totalPrice) {
        return new Price(totalPrice, BigDecimal.ZERO, DEFAULT_SHIPPING_COST);
    }

    public static Price withShippingCost(BigDecimal totalPrice, BigDecimal discountAmount) {
        return new Price(totalPrice, discountAmount, DEFAULT_SHIPPING_COST);
    }

    public static Price withFreeShipping(BigDecimal totalPrice) {
        return new Price(totalPrice, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
