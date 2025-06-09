package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;

    @NotBlank(message = "Name must be not empty.")
    @Size(max = 255, message = "Name must be less than 255 characters.")
    private String name;

    @NotNull(message = "Price must not be null.")
    @Positive(message = "Price must be greater than zero.")
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @NotBlank(message = "Unit must be not empty.")
    @Size(max = 25, message = "Unit must be less than 25 characters.")
    private String unit;

    private String description;
}
