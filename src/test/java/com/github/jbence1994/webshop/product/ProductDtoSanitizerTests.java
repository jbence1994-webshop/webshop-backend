package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductDtoTestObject.notSanitizedProductDto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.productDtoWithNullIdAndNullPhoto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class ProductDtoSanitizerTests {

    @InjectMocks
    private ProductDtoSanitizer productDtoSanitizer;

    @Test
    public void sanitizeTest() {
        var result = productDtoSanitizer.sanitize(notSanitizedProductDto());

        assertThat(result.getName(), equalTo(productDtoWithNullIdAndNullPhoto().getName()));
        assertThat(result.getUnit(), equalTo(productDtoWithNullIdAndNullPhoto().getUnit()));
        assertThat(result.getDescription(), equalTo(productDtoWithNullIdAndNullPhoto().getDescription()));
        assertThat(result.getCategory(), equalTo(productDtoWithNullIdAndNullPhoto().getCategory()));
    }
}
