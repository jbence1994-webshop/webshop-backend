package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;

public final class ProductReviewResponseTestObject {
    public static ProductReviewResponse productReviewResponse() {
        return new ProductReviewResponse(1L, PRODUCT_1_REVIEW);
    }
}
