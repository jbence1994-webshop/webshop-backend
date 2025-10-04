package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.ai.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.ai.ChatResponseTestObject.chatResponse;
import static com.github.jbence1994.webshop.product.ProductTestConstants.PRODUCT_1_REVIEW_SUMMARY;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductReviewSummarizerImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ProductReviewSummarizerImpl productReviewSummarizer;

    @Test
    public void summarizeProductReviewsTest() {
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(chatService.chat(anyString())).thenReturn(chatResponse(PRODUCT_1_REVIEW_SUMMARY));

        var result = productReviewSummarizer.summarizeProductReviews(1L);

        assertThat(result, equalTo("This is summary of reviews."));

        verify(productQueryService, times(1)).getProduct(any());
        verify(chatService, times(1)).chat(anyString());
    }
}
