package com.github.jbence1994.webshop.photo;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PHOTO_FILE_NAME;

public class DownloadPhotoDtoTestObject {
    public static DownloadPhotoDto downloadPhotoDto() {
        return new DownloadPhotoDto(PHOTO_FILE_NAME);
    }
}
