package com.github.jbence1994.webshop.user;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class TemporaryPasswordGeneratorImpl implements TemporaryPasswordGenerator {

    @Override
    @SneakyThrows
    public String generate() {
        var bytes = new byte[32];
        var secureRandom = SecureRandom.getInstanceStrong();
        secureRandom.nextBytes(bytes);

        return Base64.getEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
