package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_CREATED_AT;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_REVIEW_CREATED_AT;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithAvatar;
import static com.github.jbence1994.webshop.user.UserTestObject.user1WithoutAvatar;

public final class ProductReviewTestObject {
    public static ProductReview productReview1() {
        return new ProductReview(1L, product1(), user1WithoutAvatar(), PRODUCT_1_REVIEW, PRODUCT_1_REVIEW_CREATED_AT);
    }

    public static ProductReview productReview2() {
        return new ProductReview(2L, product1(), user1WithAvatar(), PRODUCT_2_REVIEW, PRODUCT_2_REVIEW_CREATED_AT);
    }
}
