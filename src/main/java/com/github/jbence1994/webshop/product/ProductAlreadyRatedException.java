package com.github.jbence1994.webshop.product;

public class ProductAlreadyRatedException extends RuntimeException {
    public ProductAlreadyRatedException() {
        super("You have already rated this product. If you want to change it, please update it.");
    }
}
