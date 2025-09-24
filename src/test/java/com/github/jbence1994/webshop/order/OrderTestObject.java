package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jbence1994.webshop.order.OrderItemTestObject.orderItem;
import static com.github.jbence1994.webshop.order.OrderTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.order.OrderTestConstants.DISCOUNT_AMOUNT;
import static com.github.jbence1994.webshop.order.OrderTestConstants.EARNED_LOYALTY_POINTS;
import static com.github.jbence1994.webshop.user.UserTestObject.user;

public final class OrderTestObject {
    public static Order order1() {
        return buildOrder(BigDecimal.valueOf(49.99), List.of(orderItem()));
    }

    public static Order order2() {
        return buildOrder(BigDecimal.valueOf(249.95), List.of(orderItem()));
    }

    private static Order buildOrder(BigDecimal totalPrice, List<OrderItem> orderItems) {
        return new Order(
                1L,
                user(),
                totalPrice,
                DISCOUNT_AMOUNT,
                OrderStatus.CREATED,
                EARNED_LOYALTY_POINTS,
                CREATED_AT,
                orderItems
        );
    }
}
