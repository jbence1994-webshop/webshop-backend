package com.github.jbence1994.webshop.image;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ImageUploadException extends RuntimeException {
    public ImageUploadException(String message) {
        super(message);
    }
}
