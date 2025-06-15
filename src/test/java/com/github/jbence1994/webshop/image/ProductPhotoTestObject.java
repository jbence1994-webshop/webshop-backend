package com.github.jbence1994.webshop.image;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;

public final class ProductPhotoTestObject {
    public static ProductPhoto productPhoto() {
        return new ProductPhoto(
                1L,
                product1(),
                PHOTO_FILE_NAME,
                LocalDateTime.now()
        );
    }
}
