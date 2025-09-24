package com.github.jbence1994.webshop.product;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;

public final class ProductPhotoDtoTestObject {
    public static ProductPhotoDto productPhotoDto() {
        return new ProductPhotoDto(PHOTO_FILE_NAME, PHOTO_URL);
    }
}
