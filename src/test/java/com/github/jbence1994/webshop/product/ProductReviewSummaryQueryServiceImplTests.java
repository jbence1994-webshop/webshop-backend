package com.github.jbence1994.webshop.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.jbence1994.webshop.product.ProductReviewSummaryTestObject.notExpiredProductReviewSummary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductReviewSummaryQueryServiceImplTests {

    @Mock
    private ProductReviewSummaryRepository productReviewSummaryRepository;

    @InjectMocks
    private ProductReviewSummaryQueryServiceImpl productReviewSummaryQueryService;

    @Test
    public void getProductReviewSummaryTest_HappyPath() {
        when(productReviewSummaryRepository.findByProductId(any())).thenReturn(Optional.of(notExpiredProductReviewSummary()));

        var result = productReviewSummaryQueryService.getProductReviewSummary(1L);

        assertThat(result, not(nullValue()));
    }

    @Test
    public void getProductReviewSummaryTest_UnhappyPath() {
        when(productReviewSummaryRepository.findByProductId(any())).thenReturn(Optional.empty());

        var result = productReviewSummaryQueryService.getProductReviewSummary(1L);

        assertThat(result, is(nullValue()));
    }
}
