package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductReviewRequest(

        @NotNull(message = "Product review must be provided.")
        @NotBlank(message = "Product review must be not empty.")
        String review
) {
}
