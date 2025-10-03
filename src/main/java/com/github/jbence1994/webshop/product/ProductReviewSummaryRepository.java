package com.github.jbence1994.webshop.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductReviewSummaryRepository extends JpaRepository<ProductReviewSummary, Long> {
    Optional<ProductReviewSummary> findByProductId(Long productId);
}
