package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public final class EmptyCartException extends RuntimeException {
    public EmptyCartException(UUID id) {
        super(String.format("Cart with the given ID: %s is empty.", id));
    }
}
