package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public class InvalidCheckoutSessionStateException extends RuntimeException {
    public InvalidCheckoutSessionStateException(UUID checkoutSessionId, CheckoutStatus status) {
        super(String.format("Checkout session with the given ID: %s is in the state of: %s.", checkoutSessionId, status));
    }
}
