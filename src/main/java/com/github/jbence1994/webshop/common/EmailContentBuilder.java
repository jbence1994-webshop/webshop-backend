package com.github.jbence1994.webshop.common;

public interface EmailContentBuilder {
    EmailContent buildForForgotPassword(String firstName, String rawTemporaryPassword);
}
