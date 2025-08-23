package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.common.InputSanitizer;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoSanitizer implements InputSanitizer<ProductDto> {

    @Override
    public ProductDto sanitize(ProductDto input) {
        var sanitizedName = input.getName().trim();
        var sanitizedUnit = input.getUnit().trim();
        var sanitizedDescription = input.getDescription().trim();
        var sanitizedCategory = input.getCategory().trim();

        return new ProductDto(
                input.getId(),
                sanitizedName,
                input.getPrice(),
                sanitizedUnit,
                sanitizedDescription,
                sanitizedCategory,
                input.getPhoto()
        );
    }
}
