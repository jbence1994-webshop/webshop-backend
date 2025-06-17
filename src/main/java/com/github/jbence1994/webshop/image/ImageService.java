package com.github.jbence1994.webshop.image;

public interface ImageService {
    String uploadImage(Long id, ImageUpload image);

    void deleteImage(Long id, String fileName);
}
