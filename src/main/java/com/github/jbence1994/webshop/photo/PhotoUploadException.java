package com.github.jbence1994.webshop.photo;

public class PhotoUploadException extends RuntimeException {
    public PhotoUploadException() {
        super("The photo could not be uploaded successfully.");
    }
}
