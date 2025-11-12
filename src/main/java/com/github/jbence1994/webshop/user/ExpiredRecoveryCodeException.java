package com.github.jbence1994.webshop.user;

public final class ExpiredRecoveryCodeException extends RuntimeException {
    public ExpiredRecoveryCodeException() {
        super("Recovery code has expired.");
    }
}
