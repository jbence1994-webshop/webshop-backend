package com.github.jbence1994.webshop.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordManagerImpl implements PasswordManager {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String hash(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean verify(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
