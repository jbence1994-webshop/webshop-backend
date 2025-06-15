package com.github.jbence1994.webshop.image;

import java.util.List;

public interface ProductPhotoQueryService {
    List<ProductPhoto> getProductPhotos(Long productId);
}
