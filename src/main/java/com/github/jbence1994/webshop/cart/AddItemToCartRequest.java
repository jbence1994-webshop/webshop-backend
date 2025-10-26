package com.github.jbence1994.webshop.cart;

import jakarta.validation.constraints.NotNull;

public record AddItemToCartRequest(

        @NotNull(message = "Product ID must be provided.")
        Long productId
) {
}
