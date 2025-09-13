package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.SHIPPING_COST;
import static com.github.jbence1994.webshop.order.OrderItemDtoTestObject.orderItemDto;
import static com.github.jbence1994.webshop.order.OrderTestConstants.CREATED_AT;
import static com.github.jbence1994.webshop.order.OrderTestConstants.DISCOUNT_AMOUNT;

public final class OrderDtoTestObject {
    public static OrderDto orderDto() {
        return new OrderDto(
                1L,
                BigDecimal.valueOf(49.99),
                DISCOUNT_AMOUNT,
                SHIPPING_COST,
                OrderStatus.CONFIRMED.name(),
                CREATED_AT,
                List.of(orderItemDto())
        );
    }
}
