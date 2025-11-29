package com.github.jbence1994.webshop.order;

public final class UpdateOrderStatusRequestTestObject {
    public static UpdateOrderStatusRequest updateOrderStatusRequest() {
        return new UpdateOrderStatusRequest(OrderStatus.SHIPPED);
    }
}
