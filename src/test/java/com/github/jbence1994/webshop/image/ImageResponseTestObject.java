package com.github.jbence1994.webshop.image;

import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_FILE_NAME;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PHOTO_URL;

public final class ImageResponseTestObject {
    public static ImageResponse imageResponse() {
        return new ImageResponse(
                PHOTO_FILE_NAME,
                PHOTO_URL
        );
    }
}
