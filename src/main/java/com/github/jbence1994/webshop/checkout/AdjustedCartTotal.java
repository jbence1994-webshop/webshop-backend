package com.github.jbence1994.webshop.checkout;

import java.math.BigDecimal;

public record AdjustedCartTotal(BigDecimal cartTotal, BigDecimal discountAmount) {
}
