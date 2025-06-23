package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public record PriceCalculationResult(BigDecimal totalPrice, BigDecimal discountAmount) {
}
