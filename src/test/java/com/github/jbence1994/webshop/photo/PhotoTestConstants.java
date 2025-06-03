package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface PhotoTestConstants {
    String JPG = "jpg";
    String JPEG = "jpeg";
    String PNG = "png";
    String BMP = "bmp";
    String TIFF = "tiff";
    String ORIGINAL_FILE_NAME_WITHOUT_EXTENSION = "example";
    String ORIGINAL_FILE_NAME_JPEG = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, JPEG);
    String ORIGINAL_FILE_NAME_JPG = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, JPG);
    String ORIGINAL_FILE_NAME_PNG = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, PNG);
    String ORIGINAL_FILE_NAME_BMP = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, BMP);
    String CONTENT_TYPE_IMAGE_JPEG = String.format("image/%s", JPEG);
    String CONTENT_TYPE_IMAGE_JPG = String.format("image/%s", JPG);
    String CONTENT_TYPE_IMAGE_PNG = String.format("image/%s", PNG);
    String CONTENT_TYPE_IMAGE_BMP = String.format("image/%s", BMP);
    Long FILE_SIZE = 13_266L;
    List<String> ALLOWED_FILE_EXTENSIONS = List.of(JPG, JPEG, PNG, BMP);
    String PHOTO_FILE_NAME = "143a0db8-7c91-4573-8ad0-d41738af7580.jpg";
    String PRODUCT_PHOTOS_UPLOAD_DIRECTORY_PATH = "uploads/photos/products";
    String PHOTO_URL = String.format("%s/%s/%s", "www.example.com/uploads/photos/products", PRODUCT_PHOTOS_UPLOAD_DIRECTORY_PATH, PHOTO_FILE_NAME);
}
