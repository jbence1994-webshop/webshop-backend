package com.github.jbence1994.webshop.image;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.FILE_SIZE;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.ORIGINAL_FILE_NAME_JPEG;

public final class MultipartFileTestObject {
    public static MultipartFile multipartFile() {
        return new MockMultipartFile(
                ORIGINAL_FILE_NAME_JPEG,
                ORIGINAL_FILE_NAME_JPEG,
                CONTENT_TYPE_IMAGE_JPEG,
                new byte[FILE_SIZE.intValue()]
        );
    }

    public static MultipartFile emptyMultipartFile() {
        return new MockMultipartFile(
                ORIGINAL_FILE_NAME_JPEG,
                ORIGINAL_FILE_NAME_JPEG,
                CONTENT_TYPE_IMAGE_JPEG,
                new byte[0]
        );
    }
}
