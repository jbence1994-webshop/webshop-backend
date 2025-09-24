package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductFeedbackRequest {

    @NotNull(message = "Product feedback must be provided.")
    @NotBlank(message = "Product feedback must be not empty.")
    private String feedback;
}
