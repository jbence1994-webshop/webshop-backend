package com.github.jbence1994.webshop.cart;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;

public final class CartProductDtoTestObject {
    public static CartProductDto cartProductDto1() {
        return new CartProductDto(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99)
        );
    }
}
