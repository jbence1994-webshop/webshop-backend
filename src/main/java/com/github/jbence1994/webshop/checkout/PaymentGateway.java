package com.github.jbence1994.webshop.checkout;

import java.util.Optional;

public interface PaymentGateway {
    PaymentSessionResponse createPaymentSession(PaymentSessionRequest request);

    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
