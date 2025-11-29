package com.github.jbence1994.webshop.order;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(

        @NotNull(message = "Order status must be provided.")
        OrderStatus status
) {
}
