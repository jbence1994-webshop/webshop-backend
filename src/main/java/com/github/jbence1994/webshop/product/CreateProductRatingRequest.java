package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotNull;

public record CreateProductRatingRequest(

        @NotNull(message = "Product rating value must be provided.")
        Byte value
) {
}
