package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public final class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(UUID id) {
        super(String.format("No cart was found with the given ID: %s.", id));
    }
}
