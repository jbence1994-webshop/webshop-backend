package com.github.jbence1994.webshop.user;

public final class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super(String.format("Email address '%s' is already in use. Please use a different.", email));
    }
}
