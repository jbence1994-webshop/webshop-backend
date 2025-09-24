package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_FEEDBACK;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.ProfileTestObject.bronzeProfile1;

public final class ProductFeedbackTestObject {
    public static ProductFeedback productFeedback() {
        return new ProductFeedback(1L, product1(), bronzeProfile1(), PRODUCT_1_FEEDBACK);
    }
}
