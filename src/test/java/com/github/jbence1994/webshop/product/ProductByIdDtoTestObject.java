package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductReviewDtoTestObject.productReviewDto;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;

public final class ProductByIdDtoTestObject {
    public static ProductByIdDto productByIdDtoWithPhoto() {
        return buildProduct(productPhotoDto());
    }

    public static ProductByIdDto productByIdDtoWithoutPhoto() {
        return buildProduct(null);
    }

    private static ProductByIdDto buildProduct(ProductPhotoDto photo) {
        return new ProductByIdDto(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                photo,
                List.of(productReviewDto()),
                0.0
        );
    }
}
