package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public record CartProductDto(
        Long id,
        String name,
        BigDecimal price
) {
}
