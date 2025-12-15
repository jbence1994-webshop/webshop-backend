package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EncryptedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String dateOfBirth;

    private String phoneNumber;

    private String avatarFileName;

    private int loyaltyPoints;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private EncryptedBillingAddress billingAddress;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private EncryptedShippingAddress shippingAddress;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> favoriteProducts = new ArrayList<>();

    public void earnLoyaltyPoints(int value) {
        this.loyaltyPoints += value;
    }

    public void burnLoyaltyPoints(int value) {
        this.loyaltyPoints -= value;
    }

    public MembershipTier getMembershipTier() {
        return MembershipTier.fromPoints(loyaltyPoints);
    }

    public void addFavoriteProduct(Product product) {
        favoriteProducts.add(product);
    }

    public void removeFavoriteProduct(Long productId) {
        favoriteProducts.removeIf(product -> productId.equals(product.getId()));
    }
}
