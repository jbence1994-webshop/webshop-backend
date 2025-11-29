package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public interface CartTotalAdjustmentStrategy {
    AdjustedCartTotal adjustCartTotal(BigDecimal cartTotal, BigDecimal discountValue);
}
