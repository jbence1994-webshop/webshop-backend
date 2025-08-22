package com.github.jbence1994.webshop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.name")
public record WebshopNameConfig(String value) {
}
