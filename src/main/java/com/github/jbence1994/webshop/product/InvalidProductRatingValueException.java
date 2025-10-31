package com.github.jbence1994.webshop.product;

public final class InvalidProductRatingValueException extends RuntimeException {
    public InvalidProductRatingValueException() {
        super("Rating must be between 1 and 5.");
    }
}
