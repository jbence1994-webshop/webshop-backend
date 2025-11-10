package com.github.jbence1994.webshop.checkout;

import java.util.UUID;

public class CheckoutSessionAlreadyExistsByCartIdException extends RuntimeException {
    public CheckoutSessionAlreadyExistsByCartIdException(UUID cartId) {
        super(String.format("Checkout session with the given cart ID: %s already exists.", cartId));
    }
}
