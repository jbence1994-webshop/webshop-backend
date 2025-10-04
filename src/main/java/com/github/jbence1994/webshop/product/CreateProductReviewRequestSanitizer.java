package com.github.jbence1994.webshop.product;

import org.springframework.stereotype.Component;

@Component
public class CreateProductReviewRequestSanitizer {

    public CreateProductReviewRequest sanitize(CreateProductReviewRequest request) {
        var sanitizedReview = request.getReview().trim();

        return new CreateProductReviewRequest(sanitizedReview);
    }
}
