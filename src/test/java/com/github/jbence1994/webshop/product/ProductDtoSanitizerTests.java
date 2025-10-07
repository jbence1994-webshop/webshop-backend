package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.notSanitizedProductDto;
import static com.github.jbence1994.webshop.product.ProductDtoTestObject.notSanitizedProductDtoWithNullDescription;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_DESCRIPTION;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_NAME;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_UNIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class ProductDtoSanitizerTests {

    @InjectMocks
    private ProductDtoSanitizer productDtoSanitizer;

    @Test
    public void sanitizeTest_1() {
        var result = productDtoSanitizer.sanitize(notSanitizedProductDto());

        assertThat(result.getName(), equalTo(PRODUCT_1_NAME));
        assertThat(result.getUnit(), equalTo(PRODUCT_1_UNIT));
        assertThat(result.getDescription(), equalTo(PRODUCT_1_DESCRIPTION));
        assertThat(result.getCategory(), equalTo(CATEGORY_1_NAME));
    }

    @Test
    public void sanitizeTest_2() {
        var result = productDtoSanitizer.sanitize(notSanitizedProductDtoWithNullDescription());

        assertThat(result.getName(), equalTo(PRODUCT_1_NAME));
        assertThat(result.getUnit(), equalTo(PRODUCT_1_UNIT));
        assertThat(result.getDescription(), is(nullValue()));
        assertThat(result.getCategory(), equalTo(CATEGORY_1_NAME));
    }
}
