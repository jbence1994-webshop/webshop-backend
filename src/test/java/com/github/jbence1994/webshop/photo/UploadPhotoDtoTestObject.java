package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_PNG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.CONTENT_TYPE_IMAGE_TIFF;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_PNG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ORIGINAL_FILE_NAME_TIFF;

public final class UploadPhotoDtoTestObject {
    public static UploadPhotoDto jpegUploadPhotoDto() {
        return buildUploadPhotoDto(ORIGINAL_FILE_NAME_JPEG, CONTENT_TYPE_IMAGE_JPEG);
    }

    public static UploadPhotoDto jpgUploadPhotoDto() {
        return buildUploadPhotoDto(ORIGINAL_FILE_NAME_JPG, CONTENT_TYPE_IMAGE_JPG);
    }

    public static UploadPhotoDto pngUploadPhotoDto() {
        return buildUploadPhotoDto(ORIGINAL_FILE_NAME_PNG, CONTENT_TYPE_IMAGE_PNG);
    }

    public static UploadPhotoDto bmpUploadPhotoDto() {
        return buildUploadPhotoDto(ORIGINAL_FILE_NAME_BMP, CONTENT_TYPE_IMAGE_BMP);
    }

    public static UploadPhotoDto tiffUploadPhotoDto() {
        return buildUploadPhotoDto(ORIGINAL_FILE_NAME_TIFF, CONTENT_TYPE_IMAGE_TIFF);
    }

    private static UploadPhotoDto buildUploadPhotoDto(String originalFilename, String contentType) {
        return new UploadPhotoDto(
                false,
                originalFilename,
                FILE_SIZE,
                contentType,
                new byte[FILE_SIZE.intValue()]
        );
    }
}
