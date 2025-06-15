package com.github.jbence1994.webshop.image;

public class ProductPhotoUploadException extends RuntimeException {
    public ProductPhotoUploadException() {
        super("The photo could not be uploaded successfully.");
    }
}
