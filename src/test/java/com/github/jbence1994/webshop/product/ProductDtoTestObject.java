package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;

public final class ProductDtoTestObject {
    public static ProductDto productDtoWithNullIdAndNullPhoto() {
        return buildProduct(
                null,
                PRODUCT_1_NAME,
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                null
        );
    }

    public static ProductDto productDto() {
        return buildProduct(
                1L,
                PRODUCT_1_NAME,
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                productPhotoDto()
        );
    }

    public static ProductDto notSanitizedProductDto() {
        return buildProduct(
                null,
                " " + PRODUCT_1_NAME + " ",
                " " + PRODUCT_1_UNIT + " ",
                " " + PRODUCT_1_DESCRIPTION + " ",
                " " + CATEGORY_1_NAME + " ",
                null
        );
    }

    public static ProductDto notSanitizedProductDtoWithNullDescription() {
        return buildProduct(
                null,
                " " + PRODUCT_1_NAME + " ",
                " " + PRODUCT_1_UNIT + " ",
                null,
                " " + CATEGORY_1_NAME + " ",
                null
        );
    }

    private static ProductDto buildProduct(
            Long id,
            String name,
            String unit,
            String description,
            String category,
            ProductPhotoDto photo
    ) {
        return new ProductDto(
                id,
                name,
                BigDecimal.valueOf(49.99),
                unit,
                description,
                category,
                photo,
                0.0
        );
    }
}
