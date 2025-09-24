package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.CreateProductFeedbackRequestTestObject.notSanitizedCreateProductFeedbackRequest;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_FEEDBACK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class CreateProductFeedbackRequestSanitizerTests {

    @InjectMocks
    private CreateProductFeedbackRequestSanitizer createProductFeedbackRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = createProductFeedbackRequestSanitizer.sanitize(notSanitizedCreateProductFeedbackRequest());

        assertThat(result.getFeedback(), equalTo(PRODUCT_1_FEEDBACK));
    }
}
