package com.github.jbence1994.webshop.product;

public final class ProductRatingTestObject {
    public static ProductRating productRating(byte value) {
        return new ProductRating(1L, null, null, value);
    }
}
