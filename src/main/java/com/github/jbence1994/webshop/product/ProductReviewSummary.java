package com.github.jbence1994.webshop.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_review_summaries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReviewSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private String text;

    private LocalDateTime expirationDate;

    public static ProductReviewSummary of(Product product, String text) {
        var productReviewSummary = new ProductReviewSummary();

        productReviewSummary.product = product;
        productReviewSummary.text = text;
        productReviewSummary.expirationDate = LocalDateTime.now().plusDays(7);

        return productReviewSummary;
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }
}
