package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_CREATED_AT;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;

public final class ProductReviewDtoTestObject {
    public static ProductReviewDto productReviewDto() {
        return new ProductReviewDto(
                FIRST_NAME + " " + MIDDLE_NAME + " " + LAST_NAME,
                "This wireless mouse feels very comfortable in hand and tracks smoothly on any surface. The battery lasts surprisingly long even with daily use.",
                PRODUCT_1_REVIEW_CREATED_AT
        );
    }
}
