package com.github.jbence1994.webshop.product;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;

public final class CreateProductDtoTestObject {
    public static CreateProductDto createProductDto1() {
        return new CreateProductDto(
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99),
                PRODUCT_1_UNIT,
                PRODUCT_1_DESCRIPTION
        );
    }
}
