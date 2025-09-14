package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class OrderItemTestObject {
    public static OrderItem orderItem1() {
        return buildOrderItem(1, BigDecimal.valueOf(49.99));
    }

    public static OrderItem orderItem2() {
        return buildOrderItem(5, BigDecimal.valueOf(249.95));
    }

    private static OrderItem buildOrderItem(Integer quantity, BigDecimal subTotal) {
        return new OrderItem(1L, null, product1(), product1().getPrice(), quantity, subTotal);
    }
}
