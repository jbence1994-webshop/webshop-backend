package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public interface PriceAdjustmentStrategy {
    Price adjustPrice(BigDecimal totalPrice, BigDecimal discountValue);
}
