package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface ProductPhotoQueryService {
    List<ProductPhoto> getProductPhotos(Long productId);
}
