package com.github.jbence1994.webshop.image;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.PHOTO_URL;

public final class PhotoResponseTestObject {
    public static PhotoResponse photoResponse() {
        return new PhotoResponse(
                PHOTO_FILE_NAME,
                PHOTO_URL
        );
    }
}
