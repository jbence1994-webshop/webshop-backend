package com.github.jbence1994.webshop.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("No product was found with the given ID: #%d.", id));
    }
}
