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

    public static CheckoutPrice of(BigDecimal totalPrice, BigDecimal discountAmount, BigDecimal shippingCost) {
        return new CheckoutPrice(totalPrice, discountAmount, shippingCost);
    }
}
