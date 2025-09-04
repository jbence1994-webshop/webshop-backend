package com.github.jbence1994.webshop.checkout;

public interface PaymentGateway {
    CheckoutSessionResponse createCheckoutSession(CheckoutSessionRequest request);
}
