package com.github.jbence1994.webshop.image;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }
}
