package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.expiredProductReviewSummary;
import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.notExpiredProductReviewSummary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class ProductReviewSummaryTests {

    private static Stream<Arguments> productReviewSummaryParams() {
        return Stream.of(
                Arguments.of(Named.of("Product review summary is expired", expiredProductReviewSummary()), true),
                Arguments.of(Named.of("Product review summary is not expired", notExpiredProductReviewSummary()), false)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productReviewSummaryParams")
    public void isExpiredTest(ProductReviewSummary productReviewSummary, boolean expectedResult) {
        var result = productReviewSummary.isExpired();

        assertThat(result, is(expectedResult));
    }
}
