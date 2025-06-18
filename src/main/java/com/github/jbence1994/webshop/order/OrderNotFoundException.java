package com.github.jbence1994.webshop.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super(String.format("No order was found with the given ID: #%d.", id));
    }
}
