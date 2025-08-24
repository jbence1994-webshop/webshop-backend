package com.github.jbence1994.webshop.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "webshop.image-uploads")
public record ImageUploadsConfig(
        List<String> allowedFileExtensions,
        String productPhotosDirectory,
        String profileAvatarDirectory
) {
}
