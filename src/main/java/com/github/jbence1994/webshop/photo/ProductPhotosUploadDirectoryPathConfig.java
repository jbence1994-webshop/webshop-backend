package com.github.jbence1994.webshop.photo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.photo-upload-directory-path.products")
@AllArgsConstructor
@Getter
public class ProductPhotosUploadDirectoryPathConfig {
    private String path;
}
