package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_2_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile2;

public final class ProductReviewTestObject {
    public static ProductReview productReview1() {
        return new ProductReview(1L, product1(), bronzeProfile1(), PRODUCT_1_REVIEW);
    }

    public static ProductReview productReview2() {
        return new ProductReview(2L, product1(), bronzeProfile2(), PRODUCT_2_REVIEW);
    }
}
