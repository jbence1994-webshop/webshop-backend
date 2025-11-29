package com.github.jbence1994.webshop.order;

public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(OrderStatus status) {
        super(String.format("Order with status: %s cannot changed to anything.", status));
    }

    public InvalidOrderStatusException(OrderStatus from, OrderStatus to) {
        super(String.format("Order with status: %s cannot changed to %s.", from, to));
    }
}
