package com.github.jbence1994.webshop.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class ProductPhotoUrlBuilder implements ImageUrlBuilder {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;

    @Override
    public String buildUrl(String fileName) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(productPhotosUploadDirectoryConfig.path() + "/")
                .path(fileName)
                .toUriString();
    }
}
