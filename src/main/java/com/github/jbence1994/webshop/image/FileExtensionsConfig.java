package com.github.jbence1994.webshop.image;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "webshop.file-extensions-config")
public record FileExtensionsConfig(
        List<String> allowedFileExtensions
) {
}
