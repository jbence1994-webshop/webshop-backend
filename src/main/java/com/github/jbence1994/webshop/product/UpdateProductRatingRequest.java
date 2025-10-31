package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotNull;

public record UpdateProductRatingRequest(

        @NotNull(message = "Product rating must be provided.")
        Byte ratingValue
) {
}
