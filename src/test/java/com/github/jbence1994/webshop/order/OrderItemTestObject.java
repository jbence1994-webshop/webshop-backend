package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class OrderItemTestObject {
    public static OrderItem orderItem() {
        return new OrderItem(
                1L,
                null,
                product1(),
                BigDecimal.valueOf(49.99),
                5,
                BigDecimal.valueOf(249.95)
        );
    }
}
