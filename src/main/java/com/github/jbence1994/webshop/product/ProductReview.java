package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.user.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private String text;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    public static ProductReview of(Product product, Profile profile, String text) {
        var productReview = new ProductReview();

        productReview.product = product;
        productReview.profile = profile;
        productReview.text = text;

        return productReview;
    }
}
