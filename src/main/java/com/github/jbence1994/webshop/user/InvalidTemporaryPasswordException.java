package com.github.jbence1994.webshop.user;

public final class InvalidTemporaryPasswordException extends RuntimeException {
    public InvalidTemporaryPasswordException() {
        super("Invalid temporary password.");
    }
}
