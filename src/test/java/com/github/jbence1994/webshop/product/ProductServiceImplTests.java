package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.auth.AuthService;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.expiredProductReviewSummary;
import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.notExpiredProductReviewSummary;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_SUMMARY;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithOneReview;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithRating;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1WithUpdatedRating;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

    @Mock
    private ProductReviewSummaryQueryService productReviewSummaryQueryService;

    @Mock
    private ProductReviewSummaryService productReviewSummaryService;

    @Mock
    private ProductReviewSummarizer productReviewSummarizer;

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
                Arguments.of(Named.of("Rate value is 0", (byte) 0)),
                Arguments.of(Named.of("Rate value is 6", (byte) 6))
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

        assertThat(result.getId(), equalTo(product1WithRating().getId()));
        assertThat(result.getRatings().getFirst().getValue(), equalTo(product1WithRating().getRatings().getFirst().getValue()));

        verify(productQueryService, times(1)).getProduct(any());
        verify(authService, times(1)).getCurrentUser();
        verify(productRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rateParams")
    public void createProductRatingTest_UnhappyPath_InvalidProductRateValueException(byte rateValue) {
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

        assertThat(result.getId(), equalTo(product1WithUpdatedRating().getId()));
        assertThat(result.getRatings().getFirst().getValue(), equalTo(product1WithUpdatedRating().getRatings().getFirst().getValue()));

        verify(productQueryService, times(1)).getProduct(any());
        verify(authService, times(1)).getCurrentUser();
        verify(productRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rateParams")
    public void updateProductRatingTest_UnhappyPath_InvalidProductRateValueException(byte rateValue) {
        var result = assertThrows(
                InvalidProductRateValueException.class,
                () -> productService.updateProductRating(1L, rateValue)
        );

        assertThat(result.getMessage(), equalTo("Rating must be between 1 and 5."));

        verify(productQueryService, never()).getProduct(any());
        verify(authService, never()).getCurrentUser();
        verify(productRepository, never()).save(any());
    }

    @Test
    public void createProductReviewTest() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(authService.getCurrentUser()).thenReturn(user());
        when(productRepository.save(any())).thenReturn(product1WithOneReview());

        var result = productService.createProductReview(1L, PRODUCT_1_REVIEW);

        assertThat(result.getId(), equalTo(product1WithOneReview().getId()));
        assertThat(result.getReviews().getFirst().getText(), equalTo(product1WithOneReview().getReviews().getFirst().getText()));

        verify(productQueryService, times(1)).getProduct(any());
        verify(authService, times(1)).getCurrentUser();
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void generateProductReviewSummaryTest_AlreadyExistingProductReviewSummary() {
        when(productQueryService.getProduct(any())).thenReturn(product1WithOneReview());
        when(productReviewSummaryQueryService.getProductReviewSummary(any())).thenReturn(notExpiredProductReviewSummary());

        var result = productService.generateProductReviewSummary(1L);

        assertThat(result.getText(), equalTo(PRODUCT_1_REVIEW_SUMMARY));

        verify(productQueryService, times(1)).getProduct(any());
        verify(productReviewSummaryQueryService, times(1)).getProductReviewSummary(any());
        verify(productReviewSummarizer, never()).summarizeProductReviews(any());
        verify(productReviewSummaryService, never()).createProductReviewSummary(any());
        verify(productReviewSummaryService, never()).updateProductReviewSummary(any());
    }

    @Test
    public void generateProductReviewSummaryTest_ProductReviewSummaryIsNull() {
        when(productQueryService.getProduct(any())).thenReturn(product1WithOneReview());
        when(productReviewSummaryQueryService.getProductReviewSummary(any())).thenReturn(null);
        when(productReviewSummarizer.summarizeProductReviews(any())).thenReturn(PRODUCT_1_REVIEW_SUMMARY);
        doNothing().when(productReviewSummaryService).createProductReviewSummary(any());

        var result = productService.generateProductReviewSummary(1L);

        assertThat(result.getText(), equalTo(PRODUCT_1_REVIEW_SUMMARY));

        verify(productQueryService, times(1)).getProduct(any());
        verify(productReviewSummaryQueryService, times(1)).getProductReviewSummary(any());
        verify(productReviewSummarizer, atLeast(1)).summarizeProductReviews(any());
        verify(productReviewSummaryService, times(1)).createProductReviewSummary(any());
        verify(productReviewSummaryService, never()).updateProductReviewSummary(any());
    }

    @Test
    public void generateProductReviewSummaryTest_ProductReviewSummaryIsExpired() {
        when(productQueryService.getProduct(any())).thenReturn(product1WithOneReview());
        when(productReviewSummaryQueryService.getProductReviewSummary(any())).thenReturn(expiredProductReviewSummary());
        when(productReviewSummarizer.summarizeProductReviews(any())).thenReturn(PRODUCT_1_REVIEW_SUMMARY);
        doNothing().when(productReviewSummaryService).updateProductReviewSummary(any());

        var result = productService.generateProductReviewSummary(1L);

        assertThat(result.getText(), equalTo(PRODUCT_1_REVIEW_SUMMARY));

        verify(productQueryService, times(1)).getProduct(any());
        verify(productReviewSummaryQueryService, times(1)).getProductReviewSummary(any());
        verify(productReviewSummarizer, atLeast(1)).summarizeProductReviews(any());
        verify(productReviewSummaryService, never()).createProductReviewSummary(any());
        verify(productReviewSummaryService, times(1)).updateProductReviewSummary(any());
    }
}
