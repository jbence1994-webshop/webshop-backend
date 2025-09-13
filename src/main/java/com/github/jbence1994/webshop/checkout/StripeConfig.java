package com.github.jbence1994.webshop.checkout;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${webshop.stripe.secretKey}")
    private String secretKey;

    @PostConstruct
    public void initialize() {
        Stripe.apiKey = secretKey;
    }
}
