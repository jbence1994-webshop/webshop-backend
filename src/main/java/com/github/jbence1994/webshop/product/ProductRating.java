package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.user.EncryptedUser;
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
    @JoinColumn(name = "user_id")
    private EncryptedUser user;

    private Byte value;

    public ProductRating(Product product, EncryptedUser user, Byte value) {
        this.product = product;
        this.user = user;
        this.value = value;
    }
}
