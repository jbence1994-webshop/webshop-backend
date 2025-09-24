package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_FEEDBACK;

public final class CreateProductFeedbackRequestTestObject {
    public static CreateProductFeedbackRequest createProductFeedbackRequest() {
        return new CreateProductFeedbackRequest(PRODUCT_1_FEEDBACK);
    }

    public static CreateProductFeedbackRequest notSanitizedCreateProductFeedbackRequest() {
        return new CreateProductFeedbackRequest(" " + PRODUCT_1_FEEDBACK + " ");
    }
}
