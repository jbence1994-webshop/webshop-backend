package com.github.jbence1994.webshop.product;

import org.springframework.stereotype.Component;

@Component
public class CreateProductFeedbackRequestSanitizer {

    public CreateProductFeedbackRequest sanitize(CreateProductFeedbackRequest createProductFeedbackRequest) {
        var sanitizedFeedback = createProductFeedbackRequest.getFeedback().trim();

        return new CreateProductFeedbackRequest(sanitizedFeedback);
    }
}
