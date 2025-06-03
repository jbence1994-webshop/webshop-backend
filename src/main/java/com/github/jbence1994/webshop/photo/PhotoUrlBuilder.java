package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
@AllArgsConstructor
public class PhotoUrlBuilder {
    private final ProductPhotosUploadDirectoryPathConfig productPhotosUploadDirectoryPathConfig;

    public String buildUrl(String fileName) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(productPhotosUploadDirectoryPathConfig.getPath() + "/")
                .path(fileName)
                .toUriString();
    }
}
