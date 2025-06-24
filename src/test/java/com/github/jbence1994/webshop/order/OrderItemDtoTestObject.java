package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.order.OrderProductDtoTestObject.orderProductDto;

public final class OrderItemDtoTestObject {
    public static OrderItemDto orderItemDto() {
        return new OrderItemDto(
                orderProductDto(),
                1,
                BigDecimal.valueOf(49.99)
        );
    }
}
