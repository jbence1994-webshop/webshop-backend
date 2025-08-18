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

    public ProductPhoto getFirstPhoto() {
        return photos.stream()
                .findFirst()
                .orElse(null);
    }

    public void addPhoto(String fileName) {
        var photo = new ProductPhoto();
        photo.setProduct(this);
        photo.setFileName(fileName);

        photos.add(photo);
    }

    public void removePhoto(String fileName) {
        var photo = getPhoto(fileName);

        if (photo != null) {
            photos.remove(photo);
            photo.setProduct(null);
        }
    }

    private ProductPhoto getPhoto(String fileName) {
        return photos.stream()
                .filter(photo -> photo.getFileName().equals(fileName))
                .findFirst()
                .orElse(null);
    }
}
