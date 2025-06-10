package com.github.jbence1994.webshop.cart;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long productId) {
        super(String.format("No cart item was found with the given product ID: %d.", productId));
    }
}
