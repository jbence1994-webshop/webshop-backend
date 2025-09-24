package com.github.jbence1994.webshop.checkout;

public interface PaymentGateway {
    PaymentSessionResponse createPaymentSession(PaymentSessionRequest request);

    // Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
