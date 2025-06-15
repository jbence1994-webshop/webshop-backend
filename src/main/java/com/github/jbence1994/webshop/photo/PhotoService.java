package com.github.jbence1994.webshop.photo;

public interface PhotoService {
    String uploadPhoto(Long id, UploadPhoto uploadPhoto);

    void deletePhoto(Long id, String fileName);
}
