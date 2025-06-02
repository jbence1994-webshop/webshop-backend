package com.github.jbence1994.webshop.photo;

public class ProductPhotoDeletionException extends RuntimeException {
    public ProductPhotoDeletionException() {
        super("The photo could not be deleted successfully.");
    }
}
