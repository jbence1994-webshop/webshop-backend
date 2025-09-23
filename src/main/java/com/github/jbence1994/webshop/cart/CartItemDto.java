package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

public record CartItemDto(CartProductDto product, int quantity, BigDecimal totalPrice) {
}
