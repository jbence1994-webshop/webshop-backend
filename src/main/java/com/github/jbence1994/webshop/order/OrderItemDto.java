package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

public record OrderItemDto(OrderProductDto product, int quantity, BigDecimal subTotal) {
}
