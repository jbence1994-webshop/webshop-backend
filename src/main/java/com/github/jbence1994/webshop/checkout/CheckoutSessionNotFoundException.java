package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public final class CheckoutSessionNotFoundException extends RuntimeException {
    public CheckoutSessionNotFoundException(UUID id) {
        super(String.format("No checkout session was found with the given ID: %s.", id));
    }
}
