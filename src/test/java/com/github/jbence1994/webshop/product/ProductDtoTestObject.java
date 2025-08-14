package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;

public final class ProductDtoTestObject {
    public static ProductDto productDtoWithNullId() {
        return buildProduct(null);
    }

    public static ProductDto productDto() {
        return buildProduct(1L);
    }

    private static ProductDto buildProduct(Long id) {
        return new ProductDto(
                id,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                productPhotoDto()
        );
    }
}
