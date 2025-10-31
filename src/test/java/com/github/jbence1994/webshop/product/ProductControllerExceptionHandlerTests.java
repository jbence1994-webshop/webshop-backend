package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.product.CategoryTestConstants.CATEGORY_1_NAME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class ProductControllerExceptionHandlerTests {

    @InjectMocks
    private ProductControllerExceptionHandler productControllerExceptionHandler;

    @Test
    public void handleInvalidCategoryExceptionTest() {
        var result = productControllerExceptionHandler.handleInvalidCategoryException(new InvalidCategoryException(CATEGORY_1_NAME));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Invalid category with the following name: 'Electronics'."));
    }

    @Test
    public void handleInvalidProductRatingValueExceptionTest() {
        var result = productControllerExceptionHandler.handleInvalidProductRatingValueException(new InvalidProductRatingValueException());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Rating must be between 1 and 5."));
    }

    @Test
    public void handleProductAlreadyRatedExceptionTest() {
        var result = productControllerExceptionHandler.handleProductAlreadyRatedException(new ProductAlreadyRatedException());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("You have already rated this product. If you want to change it, please update it."));
    }
}
