package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("created_at")
    private LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    @GeneratedColumn("updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public String getFirstName() {
        return profile.getFirstName();
    }

    public String getPhoneNumber() {
        return profile.getPhoneNumber();
    }

    public void setProfileAvatar(String fileName) {
        profile.setAvatarFileName(fileName);
    }

    public Optional<String> getProfileAvatar() {
        return profile.getProfileAvatar();
    }

    public void addFavoriteProduct(Product product) {
        profile.addFavoriteProduct(product);
    }

    public void removeFavoriteProduct(Long productId) {
        profile.removeFavoriteProduct(productId);
    }
}
