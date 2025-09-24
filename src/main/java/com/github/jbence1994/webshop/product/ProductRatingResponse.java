package com.github.jbence1994.webshop.product;

public record ProductRatingResponse(Long productId, Byte yourRating, double averageRating, int totalRatings) {
}
