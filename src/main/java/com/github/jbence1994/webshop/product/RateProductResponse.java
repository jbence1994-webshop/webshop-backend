package com.github.jbence1994.webshop.product;

public record RateProductResponse(
        Long productId,
        Byte yourRating,
        double averageRating,
        int totalRatings
) {
}
