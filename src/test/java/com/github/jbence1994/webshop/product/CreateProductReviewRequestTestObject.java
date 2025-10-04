package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;

public final class CreateProductReviewRequestTestObject {
    public static CreateProductReviewRequest createProductReviewRequest() {
        return new CreateProductReviewRequest(PRODUCT_1_REVIEW);
    }

    public static CreateProductReviewRequest notSanitizedCreateProductReviewRequest() {
        return new CreateProductReviewRequest(" " + PRODUCT_1_REVIEW + " ");
    }
}
