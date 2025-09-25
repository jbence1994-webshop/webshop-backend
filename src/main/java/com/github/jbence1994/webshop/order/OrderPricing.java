package com.github.jbence1994.webshop.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderPricing {
    private BigDecimal totalPrice;
    private BigDecimal totalPriceCardAmount;
    private BigDecimal totalPriceRewardPointsAmount;

    public static OrderPricing of(
            BigDecimal totalPrice,
            BigDecimal totalPriceCardAmount,
            BigDecimal totalPriceRewardPointsAmount
    ) {
        return new OrderPricing(totalPrice, totalPriceCardAmount, totalPriceRewardPointsAmount);
    }

    public static OrderPricing of() {
        return new OrderPricing();
    }
}
