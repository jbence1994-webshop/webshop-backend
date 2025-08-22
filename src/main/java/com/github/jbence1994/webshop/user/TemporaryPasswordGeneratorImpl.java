package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class TemporaryPasswordGeneratorImpl implements TemporaryPasswordGenerator {

    @Override
    public String generate() {
        var bytes = new byte[32];
        var secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);

        return Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}
