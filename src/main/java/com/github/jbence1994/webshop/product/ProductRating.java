package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.user.Profile;
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

@Entity
@Table(name = "product_ratings")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private Byte value;

    public static ProductRating of(Product product, Profile profile, Byte value) {
        var productRating = new ProductRating();

        productRating.product = product;
        productRating.profile = profile;
        productRating.value = value;

        return productRating;
    }
}
