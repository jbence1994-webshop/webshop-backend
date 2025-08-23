package com.github.jbence1994.webshop.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.mail")
public record WebshopEmailAddressConfig(String username) {
}
