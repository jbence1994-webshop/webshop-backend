package com.github.jbence1994.webshop.image;

public class ProductPhotoDeletionException extends RuntimeException {
    public ProductPhotoDeletionException() {
        super("The photo could not be deleted successfully.");
    }
}
