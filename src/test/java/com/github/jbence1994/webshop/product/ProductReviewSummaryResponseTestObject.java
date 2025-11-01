package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_SUMMARY;

public final class ProductReviewSummaryResponseTestObject {
    public static ProductReviewSummaryResponse productReviewSummaryResponse() {
        return new ProductReviewSummaryResponse(PRODUCT_1_REVIEW_SUMMARY);
    }
}
