package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

public record OrderProductDto(Long id, String name, BigDecimal price) {
}
