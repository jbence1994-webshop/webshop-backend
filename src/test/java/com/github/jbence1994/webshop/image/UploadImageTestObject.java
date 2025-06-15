package com.github.jbence1994.webshop.image;

import static com.github.jbence1994.webshop.image.ImageTestConstants.CONTENT_TYPE_IMAGE_BMP;
import static com.github.jbence1994.webshop.image.ImageTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.CONTENT_TYPE_IMAGE_JPG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.CONTENT_TYPE_IMAGE_PNG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.CONTENT_TYPE_IMAGE_TIFF;
import static com.github.jbence1994.webshop.image.ImageTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ORIGINAL_FILE_NAME_BMP;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ORIGINAL_FILE_NAME_JPEG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ORIGINAL_FILE_NAME_JPG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ORIGINAL_FILE_NAME_PNG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.ORIGINAL_FILE_NAME_TIFF;

public final class UploadImageTestObject {
    public static UploadImage jpegUploadImage() {
        return buildUploadImage(ORIGINAL_FILE_NAME_JPEG, CONTENT_TYPE_IMAGE_JPEG);
    }

    public static UploadImage jpgUploadImage() {
        return buildUploadImage(ORIGINAL_FILE_NAME_JPG, CONTENT_TYPE_IMAGE_JPG);
    }

    public static UploadImage pngUploadImage() {
        return buildUploadImage(ORIGINAL_FILE_NAME_PNG, CONTENT_TYPE_IMAGE_PNG);
    }

    public static UploadImage bmpUploadImage() {
        return buildUploadImage(ORIGINAL_FILE_NAME_BMP, CONTENT_TYPE_IMAGE_BMP);
    }

    public static UploadImage tiffUploadImage() {
        return buildUploadImage(ORIGINAL_FILE_NAME_TIFF, CONTENT_TYPE_IMAGE_TIFF);
    }

    private static UploadImage buildUploadImage(String originalFilename, String contentType) {
        return new UploadImage(
                false,
                originalFilename,
                FILE_SIZE,
                contentType,
                new byte[FILE_SIZE.intValue()]
        );
    }
}
