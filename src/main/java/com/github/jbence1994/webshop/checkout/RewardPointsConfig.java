package com.github.jbence1994.webshop.checkout;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "webshop.reward-points")
public record RewardPointsConfig(double rate) {
}
