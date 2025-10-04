package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public final class CheckoutSessionAlreadyCompletedException extends RuntimeException {
    public CheckoutSessionAlreadyCompletedException(UUID id) {
        super(String.format("Checkout session with the given ID: %s already completed.", id));
    }
}
