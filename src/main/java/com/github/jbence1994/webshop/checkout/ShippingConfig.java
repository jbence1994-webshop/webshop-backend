package com.github.jbence1994.webshop.checkout;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "webshop.shipping")
public record ShippingConfig(
        BigDecimal shippingCost,
        BigDecimal freeShippingThreshold
) {
}
