package com.github.jbence1994.webshop.cart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateCartItemRequest {

    @NotNull(message = "Quantity must be provided.")
    @Min(value = 1, message = "Quantity must be greater than zero.")
    @Max(value = 100, message = "Quantity must be less than or equal to one hundred.")
    private Integer quantity;
}
