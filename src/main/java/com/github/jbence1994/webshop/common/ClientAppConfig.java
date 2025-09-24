package com.github.jbence1994.webshop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.client-app")
public record ClientAppConfig(String url) {
}
