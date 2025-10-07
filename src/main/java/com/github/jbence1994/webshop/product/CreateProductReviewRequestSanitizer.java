package com.github.jbence1994.webshop.product;

import org.springframework.stereotype.Component;

@Component
public class CreateProductReviewRequestSanitizer {

    public CreateProductReviewRequest sanitize(CreateProductReviewRequest request) {
        return new CreateProductReviewRequest(request.getReview().trim());
    }
}
