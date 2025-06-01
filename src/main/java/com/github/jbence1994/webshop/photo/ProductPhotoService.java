package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface ProductPhotoService {
    String uploadProductPhoto(Long id, Photo photo);

    List<ProductPhoto> getProductPhotos(Long id);
}
