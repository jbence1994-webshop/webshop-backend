package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RecoveryCodeGeneratorImpl implements RecoveryCodeGenerator {

    @Override
    public String generate() {
        var code = new SecureRandom().nextInt(900_000) + 100_000;

        return String.valueOf(code);
    }
}
