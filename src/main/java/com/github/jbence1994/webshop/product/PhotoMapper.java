package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class PhotoMapper {
    private final ImageUrlBuilder imageUrlBuilder;

    public ProductPhotoDto toDto(Set<ProductPhoto> photos) {
        return photos.stream()
                .findFirst()
                .map(productPhoto ->
                        new ProductPhotoDto(
                                productPhoto.getFileName(),
                                imageUrlBuilder.buildUrl(productPhoto.getFileName())
                        )
                )
                .orElse(null);
    }
}
