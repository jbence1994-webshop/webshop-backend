package com.github.jbence1994.webshop.product;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String name) {
        super(String.format("Invalid category with the following name: '%s'.", name));
    }
}
