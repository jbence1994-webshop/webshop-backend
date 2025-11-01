package com.github.jbence1994.webshop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductByIdDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String unit;
    private String description;
    private String category;
    private ProductPhotoDto photo;
    private List<ProductReviewDto> reviews;
    private double averageRating;
}
