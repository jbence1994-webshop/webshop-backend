package com.github.jbence1994.webshop.product;

import java.time.LocalDateTime;

public interface ProductTestConstants {
    String PRODUCT_1_NAME = "Wireless Mouse";
    String PRODUCT_1_UNIT = "piece";
    String PRODUCT_1_DESCRIPTION = "High quality wireless mouse perfect for everyday use.";
    String PRODUCT_1_REVIEW = "This is a review.";
    String PRODUCT_1_REVIEW_SUMMARY = "This is summary of reviews.";
    LocalDateTime EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE = LocalDateTime.of(2025, 10, 2, 23, 59, 59);
    LocalDateTime NOT_EXPIRED_PRODUCT_1_REVIEW_SUMMARY_EXPIRATION_DATE = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
    String PRODUCT_2_NAME = "Gaming Keyboard";
    String PRODUCT_2_UNIT = "unit";
    String PRODUCT_2_DESCRIPTION = "High quality gaming keyboard perfect for everyday use.";
    String PRODUCT_2_REVIEW = "This is another review.";
}
