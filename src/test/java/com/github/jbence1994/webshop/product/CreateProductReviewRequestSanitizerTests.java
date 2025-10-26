package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.CreateProductReviewRequestTestObject.notSanitizedCreateProductReviewRequest;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class CreateProductReviewRequestSanitizerTests {

    @InjectMocks
    private CreateProductReviewRequestSanitizer createProductReviewRequestSanitizer;

    @Test
    public void sanitizeTest() {
        var result = createProductReviewRequestSanitizer.sanitize(notSanitizedCreateProductReviewRequest());

        assertThat(result.review(), equalTo(PRODUCT_1_REVIEW));
    }
}
