package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductReviewRequest {

    @NotNull(message = "Product review must be provided.")
    @NotBlank(message = "Product review must be not empty.")
    private String review;
}
