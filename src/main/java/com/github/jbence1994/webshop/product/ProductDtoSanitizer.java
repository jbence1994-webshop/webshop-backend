package com.github.jbence1994.webshop.product;

import org.springframework.stereotype.Component;

@Component
public class ProductDtoSanitizer {

    public ProductDto sanitize(ProductDto productDto) {
        return new ProductDto(
                productDto.getId(),
                productDto.getName().trim(),
                productDto.getPrice(),
                productDto.getUnit().trim(),
                productDto.getDescription() != null ? productDto.getDescription().trim() : null,
                productDto.getCategory().trim(),
                productDto.getPhoto(),
                productDto.getAverageRating()
        );
    }
}
