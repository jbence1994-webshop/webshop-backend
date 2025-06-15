package com.github.jbence1994.webshop.photo;

public class PhotoDeletionException extends RuntimeException {
    public PhotoDeletionException() {
        super("The photo could not be deleted successfully.");
    }
}
