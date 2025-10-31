package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ProductPhoto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String unit;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPhoto> photos = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductReview> reviews = new ArrayList<>();

    public void addPhoto(String fileName) {
        var photo = new ProductPhoto();
        photo.setProduct(this);
        photo.setFileName(fileName);

        photos.add(photo);
    }

    public void removePhoto(String fileName) {
        getPhoto(fileName)
                .ifPresent(photo -> {
                    photos.remove(photo);
                    photo.setProduct(null);
                });
    }

    public Optional<ProductPhoto> getFirstPhoto() {
        return photos.stream()
                .findFirst();
    }

    public void addRating(ProductRating productRating) {
        ratings.add(productRating);
    }

    public void updateRating(Long profileId, Byte ratingValue) {
        getRating(profileId)
                .ifPresent(productRating -> productRating.setValue(ratingValue));
    }

    public double calculateAverageRating() {
        return ratings.stream()
                .mapToInt(ProductRating::getValue)
                .average()
                .orElse(0.0);
    }

    public void addReview(ProductReview productReview) {
        reviews.add(productReview);
    }

    private Optional<ProductPhoto> getPhoto(String fileName) {
        return photos.stream()
                .filter(photo -> photo.getFileName().equals(fileName))
                .findFirst();
    }

    private Optional<ProductRating> getRating(Long profileId) {
        return ratings.stream()
                .filter(rating -> rating.getProfile().getUserId().equals(profileId))
                .findFirst();
    }
}
