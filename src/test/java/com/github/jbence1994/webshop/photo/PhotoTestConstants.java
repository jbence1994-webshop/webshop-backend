package com.github.jbence1994.webshop.photo;

import java.util.List;

public interface PhotoTestConstants {
    String JPG = "jpg";
    String JPEG = "jpeg";
    String PNG = "png";
    String BMP = "bmp";
    String TIFF = "tiff";
    String ORIGINAL_FILE_NAME_WITHOUT_EXTENSION = "example.jpg";
    String ORIGINAL_FILE_NAME_JPEG = String.format("%s.%s", ORIGINAL_FILE_NAME_WITHOUT_EXTENSION, JPEG);
    String CONTENT_TYPE_IMAGE_JPEG = String.format("image/%s", JPEG);
    Long FILE_SIZE = 13_266L;
    List<String> ALLOWED_FILE_EXTENSIONS = List.of(JPG, JPEG, PNG, BMP);
    String PHOTO_FILE_NAME = "143a0db8-7c91-4573-8ad0-d41738af7580.jpg";
    String PHOTO_URL = String.format("%s%s", "www.example.com/uploads/photos/products/", PHOTO_FILE_NAME);
}
