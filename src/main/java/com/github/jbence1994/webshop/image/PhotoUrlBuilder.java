package com.github.jbence1994.webshop.image;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@AllArgsConstructor
public class PhotoUrlBuilder {
    private final ProductPhotosUploadDirectoryConfig productPhotosUploadDirectoryConfig;

    public String buildUrl(String fileName) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(productPhotosUploadDirectoryConfig.getPath() + "/")
                .path(fileName)
                .toUriString();
    }
}
