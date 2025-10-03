package com.github.jbence1994.webshop.user;

public final class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("No user was found with the given ID: #%d.", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("No user was found with the given e-mail: '%s'.", email));
    }
}
