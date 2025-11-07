package com.github.jbence1994.webshop.checkout;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AdjustedCartTotal {
    private BigDecimal cartTotal;
    private BigDecimal discountAmount;

    public static AdjustedCartTotal of(BigDecimal cartTotal, BigDecimal discountAmount) {
        return new AdjustedCartTotal(cartTotal, discountAmount);
    }
}
