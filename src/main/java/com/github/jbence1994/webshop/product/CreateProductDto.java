package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductDto {
    private String name;
    private BigDecimal price;
    private String unit;
    private String description;
}
