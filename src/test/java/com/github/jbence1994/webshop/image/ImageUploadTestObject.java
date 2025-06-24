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

public final class ImageUploadTestObject {
    public static ImageUpload jpegImageUpload() {
        return buildImageUpload(ORIGINAL_FILE_NAME_JPEG, CONTENT_TYPE_IMAGE_JPEG);
    }

    public static ImageUpload jpgImageUpload() {
        return buildImageUpload(ORIGINAL_FILE_NAME_JPG, CONTENT_TYPE_IMAGE_JPG);
    }

    public static ImageUpload pngImageUpload() {
        return buildImageUpload(ORIGINAL_FILE_NAME_PNG, CONTENT_TYPE_IMAGE_PNG);
    }

    public static ImageUpload bmpImageUpload() {
        return buildImageUpload(ORIGINAL_FILE_NAME_BMP, CONTENT_TYPE_IMAGE_BMP);
    }

    public static ImageUpload tiffImageUpload() {
        return buildImageUpload(ORIGINAL_FILE_NAME_TIFF, CONTENT_TYPE_IMAGE_TIFF);
    }

    private static ImageUpload buildImageUpload(String originalFilename, String contentType) {
        var imageUpload = new ImageUpload();
        imageUpload.setEmpty(false);
        imageUpload.setOriginalFilename(originalFilename);
        imageUpload.setSize(FILE_SIZE);
        imageUpload.setContentType(contentType);
        imageUpload.setInputStreamBytes(new byte[FILE_SIZE.intValue()]);
        return imageUpload;
    }
}
