package com.github.jbence1994.webshop.product;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.product.ProductTestConstants.EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.product.ProductTestConstants.NOT_EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_SUMMARY;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class ProductReviewSummaryTestObject {
    public static ProductReviewSummary expiredProductReviewSummary() {
        return buildProductReviewSummary(EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE);
    }

    public static ProductReviewSummary notExpiredProductReviewSummary() {
        return buildProductReviewSummary(NOT_EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE);
    }

    private static ProductReviewSummary buildProductReviewSummary(LocalDateTime expirationDate) {
        return new ProductReviewSummary(1L, product1(), PRODUCT_1_REVIEW_SUMMARY, expirationDate);
    }
}
