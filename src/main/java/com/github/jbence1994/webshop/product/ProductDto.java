package com.github.jbence1994.webshop.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String unit;
    private String description;
}
