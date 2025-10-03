package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;

public final class ProductReviewTestObject {
    public static ProductReview productReview() {
        return new ProductReview(1L, product1(), bronzeProfile1(), PRODUCT_1_REVIEW);
    }
}
