package com.github.jbence1994.webshop.product;

import java.util.Optional;

public interface ProductReviewSummaryQueryService {
    Optional<ProductReviewSummary> getProductReviewSummary(Long productId);
}
