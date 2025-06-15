package com.github.jbence1994.webshop.image;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FileNameGeneratorImpl implements FileNameGenerator {

    @Override
    public String generate(String extension) {
        return UUID.randomUUID() + "." + extension;
    }
}
