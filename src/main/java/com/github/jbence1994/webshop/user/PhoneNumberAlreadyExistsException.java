package com.github.jbence1994.webshop.user;

public final class PhoneNumberAlreadyExistsException extends RuntimeException {
    public PhoneNumberAlreadyExistsException() {
        super("Phone number is already registered.");
    }
}
