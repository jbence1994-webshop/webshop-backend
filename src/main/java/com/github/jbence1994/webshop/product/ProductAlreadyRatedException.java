package com.github.jbence1994.webshop.product;

public class ProductAlreadyRatedException extends RuntimeException {
    public ProductAlreadyRatedException(Long productId) {
        super(String.format("You have already rated this product with the given ID: #%d.", productId));
    }
}
