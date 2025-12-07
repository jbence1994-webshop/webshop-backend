package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_CREATED_AT;

public final class ProductReviewDtoTestObject {
    public static ProductReviewDto productReviewDto() {
        return new ProductReviewDto(
                "This wireless mouse feels very comfortable in hand and tracks smoothly on any surface. The battery lasts surprisingly long even with daily use.",
                PRODUCT_1_REVIEW_CREATED_AT
        );
    }
}
