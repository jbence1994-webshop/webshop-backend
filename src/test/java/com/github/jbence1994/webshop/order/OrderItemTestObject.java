package com.github.jbence1994.webshop.order;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class OrderItemTestObject {
    public static OrderItem orderItem1() {
        return buildOrderItem(1);
    }

    public static OrderItem orderItem2() {
        return buildOrderItem(5);
    }

    private static OrderItem buildOrderItem(Integer quantity) {
        return new OrderItem(product1(), quantity);
    }
}
