package com.github.jbence1994.webshop.user;

public final class InvalidRecoveryCodeException extends RuntimeException {
    public InvalidRecoveryCodeException() {
        super("Invalid recovery code.");
    }
}
