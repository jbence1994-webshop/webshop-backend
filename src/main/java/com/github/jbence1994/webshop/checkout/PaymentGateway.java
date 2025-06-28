package com.github.jbence1994.webshop.checkout;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSessionResponse createCheckoutSession(CheckoutSessionRequest request);

    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
