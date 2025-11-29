package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jbence1994.webshop.order.OrderItemTestObject.orderItem;
import static com.github.jbence1994.webshop.order.OrderTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.order.OrderTestConstants.DISCOUNT_AMOUNT;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;

public final class OrderTestObject {
    public static Order createdOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.CREATED, List.of(orderItem()));
    }

    public static Order canceledOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.CANCELED, List.of(orderItem()));
    }

    public static Order failedOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.FAILED, List.of(orderItem()));
    }

    public static Order confirmedOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.CONFIRMED, List.of(orderItem()));
    }

    public static Order shippedOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.SHIPPED, List.of(orderItem()));
    }

    public static Order deliveredOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.DELIVERED, List.of(orderItem()));
    }

    public static Order returnedOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.RETURNED, List.of(orderItem()));
    }

    public static Order refundedOrder1() {
        return buildOrder(BigDecimal.valueOf(49.99), OrderStatus.REFUNDED, List.of(orderItem()));
    }

    public static Order createdOrder2() {
        return buildOrder(BigDecimal.valueOf(249.95), OrderStatus.CREATED, List.of(orderItem()));
    }

    private static Order buildOrder(
            BigDecimal totalPrice,
            OrderStatus orderStatus,
            List<OrderItem> orderItems
    ) {
        return new Order(
                1L,
                user1WithoutAvatar(),
                totalPrice,
                DISCOUNT_AMOUNT,
                orderStatus,
                CREATED_AT,
                orderItems
        );
    }
}
