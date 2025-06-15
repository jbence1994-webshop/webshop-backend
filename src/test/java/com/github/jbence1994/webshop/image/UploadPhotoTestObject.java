package com.github.jbence1994.webshop.image;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_BMP;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_PNG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_TIFF;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_BMP;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_JPEG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_JPG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_PNG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_TIFF;

public final class UploadPhotoTestObject {
    public static UploadPhoto jpegUploadPhoto() {
        return buildUploadPhoto(ORIGINAL_FILE_NAME_JPEG, CONTENT_TYPE_IMAGE_JPEG);
    }

    public static UploadPhoto jpgUploadPhoto() {
        return buildUploadPhoto(ORIGINAL_FILE_NAME_JPG, CONTENT_TYPE_IMAGE_JPG);
    }

    public static UploadPhoto pngUploadPhoto() {
        return buildUploadPhoto(ORIGINAL_FILE_NAME_PNG, CONTENT_TYPE_IMAGE_PNG);
    }

    public static UploadPhoto bmpUploadPhoto() {
        return buildUploadPhoto(ORIGINAL_FILE_NAME_BMP, CONTENT_TYPE_IMAGE_BMP);
    }

    public static UploadPhoto tiffUploadPhoto() {
        return buildUploadPhoto(ORIGINAL_FILE_NAME_TIFF, CONTENT_TYPE_IMAGE_TIFF);
    }

    private static UploadPhoto buildUploadPhoto(String originalFilename, String contentType) {
        return new UploadPhoto(
                false,
                originalFilename,
                FILE_SIZE,
                contentType,
                new byte[FILE_SIZE.intValue()]
        );
    }
}
