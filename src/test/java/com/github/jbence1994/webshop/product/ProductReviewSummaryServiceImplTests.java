package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.notExpiredProductReviewSummary;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductReviewSummaryServiceImplTests {

    @Mock
    private ProductReviewSummaryRepository productReviewSummaryRepository;

    @InjectMocks
    private ProductReviewSummaryServiceImpl productReviewSummaryService;

    @Test
    public void createProductReviewSummaryTest() {
        when(productReviewSummaryRepository.save(any())).thenReturn(notExpiredProductReviewSummary());

        assertDoesNotThrow(() -> productReviewSummaryService.createProductReviewSummary(notExpiredProductReviewSummary()));
    }

    @Test
    public void updateProductReviewSummaryTest() {
        when(productReviewSummaryRepository.save(any())).thenReturn(notExpiredProductReviewSummary());

        assertDoesNotThrow(() -> productReviewSummaryService.updateProductReviewSummary(notExpiredProductReviewSummary()));
    }
}
