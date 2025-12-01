package com.github.jbence1994.webshop.user;

public final class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email address is already in use.");
    }
}
