package com.github.jbence1994.webshop.product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductRatingRequest {

    @NotNull(message = "Product rate value must be provided.")
    private Byte rateValue;
}
