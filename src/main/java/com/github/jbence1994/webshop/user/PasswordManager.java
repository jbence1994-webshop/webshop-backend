package com.github.jbence1994.webshop.user;

public interface PasswordManager {
    String encode(String rawPassword);

    boolean verify(String rawPassword, String hashedPassword);
}
