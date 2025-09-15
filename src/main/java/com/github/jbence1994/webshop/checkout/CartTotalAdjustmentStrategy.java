package com.github.jbence1994.webshop.checkout;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;

public interface CartTotalAdjustmentStrategy {
    Pair<BigDecimal, BigDecimal> adjustCartTotal(BigDecimal cartTotal, BigDecimal discountValue);
}
