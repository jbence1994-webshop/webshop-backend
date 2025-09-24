package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_FEEDBACK;

public final class ProductFeedbackResponseTestObject {
    public static ProductFeedbackResponse productFeedbackResponse() {
        return new ProductFeedbackResponse(1L, PRODUCT_1_FEEDBACK);
    }
}
