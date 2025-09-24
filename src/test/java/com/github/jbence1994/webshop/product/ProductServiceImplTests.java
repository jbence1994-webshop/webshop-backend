package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductRatingResponseTestObject.productRatingResponse;
import static com.github.jbence1994.webshop.product.ProductRatingResponseTestObject.updatedProductRatingResponse;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithRating;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithUpdatedRating;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private ProductServiceImpl productService;

    private static Stream<Arguments> rateParams() {
        return Stream.of(
                Arguments.of("Rate value is 0", (byte) 0),
                Arguments.of("Rate value is 6", (byte) 6)
        );
    }

    @Test
    public void createProductTest() {
        when(productRepository.save(any())).thenReturn(product1());

        assertDoesNotThrow(() -> productService.createProduct(product1()));
    }

    @Test
    public void updateProductTest() {
        when(productRepository.save(any())).thenReturn(product1());

        assertDoesNotThrow(() -> productService.updateProduct(product1()));
    }

    @Test
    public void createProductRatingTest_HappyPath() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(authService.getCurrentUser()).thenReturn(user());
        when(productRepository.save(any())).thenReturn(product1WithRating());

        var result = productService.createProductRating(1L, (byte) 5);

        assertThat(result.productId(), equalTo(productRatingResponse().productId()));
        assertThat(result.yourRating(), equalTo(productRatingResponse().yourRating()));
        assertThat(result.averageRating(), equalTo(productRatingResponse().averageRating()));
        assertThat(result.totalRatings(), equalTo(productRatingResponse().totalRatings()));

        verify(productQueryService, times(1)).getProduct(any());
        verify(authService, times(1)).getCurrentUser();
        verify(productRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rateParams")
    public void createProductRatingTest_UnhappyPath_InvalidProductRateValueException(
            String testCase,
            byte rateValue
    ) {
        var result = assertThrows(
                InvalidProductRateValueException.class,
                () -> productService.createProductRating(1L, rateValue)
        );

        assertThat(result.getMessage(), equalTo("Rating must be between 1 and 5."));

        verify(productQueryService, never()).getProduct(any());
        verify(authService, never()).getCurrentUser();
        verify(productRepository, never()).save(any());
    }

    @Test
    public void updateProductRatingTest_HappyPath() {
        when(productQueryService.getProduct(any())).thenReturn(product1WithRating());
        when(authService.getCurrentUser()).thenReturn(user());
        when(productRepository.save(any())).thenReturn(product1WithUpdatedRating());

        var result = productService.updateProductRating(1L, (byte) 4);

        assertThat(result.productId(), equalTo(updatedProductRatingResponse().productId()));
        assertThat(result.yourRating(), equalTo(updatedProductRatingResponse().yourRating()));
        assertThat(result.averageRating(), equalTo(updatedProductRatingResponse().averageRating()));
        assertThat(result.totalRatings(), equalTo(updatedProductRatingResponse().totalRatings()));

        verify(productQueryService, times(1)).getProduct(any());
        verify(authService, times(1)).getCurrentUser();
        verify(productRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rateParams")
    public void updateProductRatingTest_UnhappyPath_InvalidProductRateValueException(
            String testCase,
            byte rateValue
    ) {
        var result = assertThrows(
                InvalidProductRateValueException.class,
                () -> productService.updateProductRating(1L, rateValue)
        );

        assertThat(result.getMessage(), equalTo("Rating must be between 1 and 5."));

        verify(productQueryService, never()).getProduct(any());
        verify(authService, never()).getCurrentUser();
        verify(productRepository, never()).save(any());
    }
}
