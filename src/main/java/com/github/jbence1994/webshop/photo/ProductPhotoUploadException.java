package com.github.jbence1994.webshop.photo;

public class ProductPhotoUploadException extends RuntimeException {
    public ProductPhotoUploadException() {
        super("The photo could not be uploaded successfully.");
    }
}
