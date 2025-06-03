package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_PNG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_PNG;

public final class PhotoTestObject {
    public static Photo jpegPhoto() {
        return buildPhoto(ORIGINAL_FILE_NAME_JPEG, CONTENT_TYPE_IMAGE_JPEG);
    }

    public static Photo jpgPhoto() {
        return buildPhoto(ORIGINAL_FILE_NAME_JPG, CONTENT_TYPE_IMAGE_JPG);
    }

    public static Photo pngPhoto() {
        return buildPhoto(ORIGINAL_FILE_NAME_PNG, CONTENT_TYPE_IMAGE_PNG);
    }

    public static Photo bmpPhoto() {
        return buildPhoto(ORIGINAL_FILE_NAME_BMP, CONTENT_TYPE_IMAGE_BMP);
    }

    private static Photo buildPhoto(String originalFilename, String contentType) {
        return new Photo(
                false,
                originalFilename,
                FILE_SIZE,
                contentType,
                new byte[FILE_SIZE.intValue()]
        );
    }
}
