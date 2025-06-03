package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class ProductPhotoTestObject {
    public static ProductPhoto productPhoto() {
        return new ProductPhoto(
                1L,
                product1(),
                PHOTO_FILE_NAME
        );
    }
}
