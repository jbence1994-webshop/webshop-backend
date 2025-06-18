package com.github.jbence1994.webshop.cart;

import java.util.UUID;

public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException(UUID id) {
        super(String.format("Cart with the given ID: %s is empty.", id));
    }
}
