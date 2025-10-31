package com.github.jbence1994.webshop.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductReviewSummaryQueryServiceImpl implements ProductReviewSummaryQueryService {
    private final ProductReviewSummaryRepository productReviewSummaryRepository;

    @Override
    public Optional<ProductReviewSummary> getProductReviewSummary(Long productId) {
        return productReviewSummaryRepository.findByProductId(productId);
    }
}
