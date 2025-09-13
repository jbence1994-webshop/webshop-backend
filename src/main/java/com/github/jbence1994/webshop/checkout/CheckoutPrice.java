package com.github.jbence1994.webshop.checkout;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CheckoutPrice {
    private BigDecimal totalPrice;
    private BigDecimal discountAmount;
    private BigDecimal shippingCost;

    private static final BigDecimal DEFAULT_SHIPPING_COST = BigDecimal.valueOf(7.99);

    public static CheckoutPrice withShippingCost(BigDecimal totalPrice) {
        return new CheckoutPrice(totalPrice, BigDecimal.ZERO, DEFAULT_SHIPPING_COST);
    }

    public static CheckoutPrice withShippingCost(BigDecimal totalPrice, BigDecimal discountAmount) {
        return new CheckoutPrice(totalPrice, discountAmount, DEFAULT_SHIPPING_COST);
    }

    public static CheckoutPrice withFreeShipping(BigDecimal totalPrice) {
        return new CheckoutPrice(totalPrice, BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
