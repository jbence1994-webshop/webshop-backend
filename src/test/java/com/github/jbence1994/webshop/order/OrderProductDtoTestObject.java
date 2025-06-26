package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;

public final class OrderProductDtoTestObject {
    public static OrderProductDto orderProductDto() {
        return new OrderProductDto(
                1L,
                PRODUCT_1_NAME,
                BigDecimal.valueOf(49.99)
        );
    }
}
