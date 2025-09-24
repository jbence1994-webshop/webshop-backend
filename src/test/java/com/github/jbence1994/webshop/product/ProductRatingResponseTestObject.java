package com.github.jbence1994.webshop.product;

public final class ProductRatingResponseTestObject {
    public static ProductRatingResponse productRatingResponse() {
        return new ProductRatingResponse(1L, (byte) 5, 5.0, 1);
    }

    public static ProductRatingResponse updatedProductRatingResponse() {
        return new ProductRatingResponse(1L, (byte) 4, 4.0, 1);
    }
}
