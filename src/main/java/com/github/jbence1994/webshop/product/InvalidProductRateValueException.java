package com.github.jbence1994.webshop.product;

public final class InvalidProductRateValueException extends RuntimeException {
    public InvalidProductRateValueException() {
        super("Rating must be between 1 and 5.");
    }
}
