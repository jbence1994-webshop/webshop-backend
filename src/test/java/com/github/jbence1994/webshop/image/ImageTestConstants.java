package com.github.jbence1994.webshop.image;

import java.util.List;

public interface ImageTestConstants {
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
    String ORIGINAL_FILE_NAME_TIFF = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, TIFF);
    String CONTENT_TYPE_IMAGE_JPEG = String.format("image/%s", JPEG);
    String CONTENT_TYPE_IMAGE_JPG = String.format("image/%s", JPG);
    String CONTENT_TYPE_IMAGE_PNG = String.format("image/%s", PNG);
    String CONTENT_TYPE_IMAGE_BMP = String.format("image/%s", BMP);
    String CONTENT_TYPE_IMAGE_TIFF = String.format("image/%s", TIFF);
    Long FILE_SIZE = 13_266L;
    List<String> ALLOWED_FILE_EXTENSIONS = List.of(JPG, JPEG, PNG, BMP);
    String PHOTO_FILE_NAME = "143a0db8-7c91-4573-8ad0-d41738af7580.jpg";
    String AVATAR_FILE_NAME = "9f42a4ea-37ba-402e-b165-1f9b2ba13e60.jpg";
    String PHOTO_NOT_EXISTING_FILE_NAME = "143a0db8-7c91-4573-8ad0-d41738af7580.bmp";
    String PRODUCT_PHOTOS_DIRECTORY = "uploads/photos/products";
    String USER_AVATAR_DIRECTORY = "uploads/avatars/users";
    String PHOTO_URL = String.format("%s/%s/%s", "www.example.com/uploads/photos/products", PRODUCT_PHOTOS_DIRECTORY, PHOTO_FILE_NAME);
    String AVATAR_URL = String.format("%s/%s/%s", "www.example.com/uploads/avatars/users", USER_AVATAR_DIRECTORY, AVATAR_FILE_NAME);
}
