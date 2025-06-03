package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_JPEG;

public final class PhotoTestObject {
    public static Photo photo() {
        return new Photo(
                false,
                ORIGINAL_FILE_NAME_JPEG,
                FILE_SIZE,
                CONTENT_TYPE_IMAGE_JPEG,
                new byte[FILE_SIZE.intValue()]
        );
    }
}
