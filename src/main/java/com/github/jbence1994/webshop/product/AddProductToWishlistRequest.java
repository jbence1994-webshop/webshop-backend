package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotNull;

public record AddProductToWishlistRequest(

        @NotNull(message = "Product ID must be provided.")
        Long productId
) {
}
