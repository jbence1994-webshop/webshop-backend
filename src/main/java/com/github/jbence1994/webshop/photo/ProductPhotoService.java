package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface ProductPhotoService {
    String uploadProductPhoto(Long productId, Photo photo);

    List<ProductPhoto> getProductPhotos(Long productId);

    void deleteProductPhoto(Long productId, String fileName);
}
