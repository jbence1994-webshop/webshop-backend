package com.github.jbence1994.webshop.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.product-photos-upload-directory-config")
public record ProductPhotosUploadDirectoryConfig(String path) {
}
