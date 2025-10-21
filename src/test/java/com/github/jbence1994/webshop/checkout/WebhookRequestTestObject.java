package com.github.jbence1994.webshop.checkout;

import java.util.Map;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.STRIPE_PAYLOAD;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.STRIPE_SIGNATURE;

public final class WebhookRequestTestObject {
    public static WebhookRequest webhookRequest() {
        return new WebhookRequest(Map.of("stripe-signature", STRIPE_SIGNATURE), STRIPE_PAYLOAD);
    }
}
