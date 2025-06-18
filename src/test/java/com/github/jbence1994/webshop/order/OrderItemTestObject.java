package com.github.jbence1994.webshop.order;

import static com.github.jbence1994.webshop.order.OrderTestConstants.TOTAL_PRICE;
import static com.github.jbence1994.webshop.order.OrderTestConstants.UNIT_PRICE;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class OrderItemTestObject {
    public static OrderItem orderItem() {
        return new OrderItem(
                1L,
                null,
                product1(),
                UNIT_PRICE,
                1,
                TOTAL_PRICE
        );
    }
}
