package com.github.jbence1994.webshop.user;

public class InvalidTemporaryPasswordException extends RuntimeException {
    public InvalidTemporaryPasswordException() {
        super("Invalid temporary password.");
    }
}
