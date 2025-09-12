package com.github.jbence1994.webshop.checkout;

public interface CheckoutService {
    CompleteCheckoutSessionResponse completeCheckoutSession(CompleteCheckoutSessionRequest request);
}
