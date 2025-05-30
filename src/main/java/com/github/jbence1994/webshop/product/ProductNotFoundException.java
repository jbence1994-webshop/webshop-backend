package com.github.jbence1994.webshop.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("Product not found by the given id: %d", id));
    }
}
