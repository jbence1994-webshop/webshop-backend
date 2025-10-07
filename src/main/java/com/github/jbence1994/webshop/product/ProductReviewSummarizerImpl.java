package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.ai.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductReviewSummarizerImpl implements ProductReviewSummarizer {
    private final ProductQueryService productQueryService;
    private final ChatService chatService;

    @Override
    public String summarizeProductReviews(Long productId) {
        var product = productQueryService.getProduct(productId);

        var reviewsAsText = product.getReviews().stream()
                .map(ProductReview::getText)
                .collect(Collectors.joining("\n\n"));

        var prompt = """
                Below you can see review(s) of a product, called: %s.
                
                Summarize it/them:
                
                %s
                """
                .formatted(product.getName(), reviewsAsText);

        return chatService.chat(prompt);
    }
}
