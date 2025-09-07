package com.github.jbence1994.webshop.product;

import org.springframework.stereotype.Component;

@Component
public class ProductDtoSanitizer {

    public ProductDto sanitize(ProductDto productDto) {
        var sanitizedName = productDto.getName().trim();
        var sanitizedUnit = productDto.getUnit().trim();
        var sanitizedDescription = productDto.getDescription().trim();
        var sanitizedCategory = productDto.getCategory().trim();

        return new ProductDto(
                productDto.getId(),
                sanitizedName,
                productDto.getPrice(),
                sanitizedUnit,
                sanitizedDescription,
                sanitizedCategory,
                productDto.getPhoto()
        );
    }
}
