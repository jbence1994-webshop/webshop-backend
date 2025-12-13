package com.github.jbence1994.webshop.user;

public final class ExpiredTemporaryPasswordException extends RuntimeException {
    public ExpiredTemporaryPasswordException() {
        super("Temporary password has expired.");
    }
}
