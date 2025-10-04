package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public final class ExpiredCheckoutSessionException extends RuntimeException {
    public ExpiredCheckoutSessionException(UUID id) {
        super(String.format("Checkout session with the given ID: %s has expired.", id));
    }
}
