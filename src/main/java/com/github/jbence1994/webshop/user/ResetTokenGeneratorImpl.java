package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ResetTokenGeneratorImpl implements ResetTokenGenerator {

    @Override
    public String generate() {
        var bytes = new byte[32];
        new SecureRandom().nextBytes(bytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
