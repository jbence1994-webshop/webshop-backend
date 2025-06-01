package com.github.jbence1994.webshop.photo;

public class ProductPhotoUploadException extends RuntimeException {
    public ProductPhotoUploadException() {
        super("Uploading the photo was not successful.");
    }
}
