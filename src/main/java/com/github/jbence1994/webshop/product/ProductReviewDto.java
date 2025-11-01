package com.github.jbence1994.webshop.product;

import java.time.LocalDateTime;

public record ProductReviewDto(String name, String text, LocalDateTime createdAt) {
}
