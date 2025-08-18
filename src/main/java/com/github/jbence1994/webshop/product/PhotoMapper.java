package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoMapper {
    private final ImageUrlBuilder imageUrlBuilder;

    public ProductPhotoDto toDto(List<ProductPhoto> photos) {
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
