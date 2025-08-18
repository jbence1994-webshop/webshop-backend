package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PhotoMapper {
    private final ImageUrlBuilder imageUrlBuilder;

    public ProductPhotoDto toDto(ProductPhoto photo) {
        return Optional.ofNullable(photo)
                .map(productPhoto ->
                        new ProductPhotoDto(
                                productPhoto.getFileName(),
                                imageUrlBuilder.buildUrl(productPhoto.getFileName())
                        )
                )
                .orElse(null);
    }
}
