package com.github.jbence1994.webshop.loyalty;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.loyalty")
public record LoyaltyConfig(int pointsRate) {
}
