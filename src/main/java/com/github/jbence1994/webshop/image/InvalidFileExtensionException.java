package com.github.jbence1994.webshop.image;

public final class InvalidFileExtensionException extends RuntimeException {
    public InvalidFileExtensionException(String extension) {
        super(String.format("Invalid file extension: .%s", extension));
    }
}
