package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotNull;

public record DeleteProductFromWishlistRequest(

        @NotNull(message = "Product ID must be provided.")
        Long productId
) {
}
