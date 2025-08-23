package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.ProductPhotoDtoTestObject.productPhotoDto;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_PRICE;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;

public final class ProductDtoTestObject {
    public static ProductDto notSanitizedProductDto() {
        return new ProductDto(
                null,
                " " + PRODUCT_1_NAME + " ",
                PRODUCT_1_PRICE,
                " " + PRODUCT_1_UNIT + " ",
                " " + PRODUCT_1_DESCRIPTION + " ",
                " " + CATEGORY_1_NAME + " ",
                null
        );
    }

    public static ProductDto productDtoWithNullIdAndNullPhoto() {
        return new ProductDto(
                null,
                PRODUCT_1_NAME,
                PRODUCT_1_PRICE,
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                null
        );
    }

    public static ProductDto productDto() {
        return new ProductDto(
                1L,
                PRODUCT_1_NAME,
                PRODUCT_1_PRICE,
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION,
                CATEGORY_1_NAME,
                productPhotoDto()
        );
    }
}
