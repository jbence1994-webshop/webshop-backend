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

    public static Price of(BigDecimal totalPrice) {
        return new Price(totalPrice, BigDecimal.ZERO);
    }

    public static Price of(BigDecimal totalPrice, BigDecimal discountAmount) {
        return new Price(totalPrice, discountAmount);
    }
}
