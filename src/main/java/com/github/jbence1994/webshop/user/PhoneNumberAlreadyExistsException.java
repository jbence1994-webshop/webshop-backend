package com.github.jbence1994.webshop.user;

public final class PhoneNumberAlreadyExistsException extends RuntimeException {
    public PhoneNumberAlreadyExistsException(String phoneNumber) {
        super(String.format("Phone number '%s' is already registered. Please use a different.", phoneNumber));
    }
}
