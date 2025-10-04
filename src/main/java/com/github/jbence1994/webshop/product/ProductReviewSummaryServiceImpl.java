package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductReviewSummaryServiceImpl implements ProductReviewSummaryService {
    private final ProductReviewSummaryRepository productReviewSummaryRepository;

    @Override
    public void createProductReviewSummary(ProductReviewSummary productReviewSummary) {
        save(productReviewSummary);
    }

    @Override
    public void updateProductReviewSummary(ProductReviewSummary productReviewSummary) {
        save(productReviewSummary);
    }

    private void save(ProductReviewSummary productReviewSummary) {
        productReviewSummaryRepository.save(productReviewSummary);
    }
}
