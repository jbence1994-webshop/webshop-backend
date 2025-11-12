package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RecoveryCodeGeneratorImpl implements RecoveryCodeGenerator {

    @Override
    public String generate() {
        var recoveryCodeValue = new SecureRandom().nextInt(1_000_000);

        return String.format("%06d", recoveryCodeValue);
    }
}
