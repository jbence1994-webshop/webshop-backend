package com.github.jbence1994.webshop.photo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PNG;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.bmpUploadPhotoDto;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.jpegUploadPhotoDto;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.jpgUploadPhotoDto;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.pngUploadPhotoDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UploadPhotoDtoTests {
    private static Stream<Arguments> uploadPhotoDtoParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegUploadPhotoDto()),
                Arguments.of(JPG, jpgUploadPhotoDto()),
                Arguments.of(PNG, pngUploadPhotoDto()),
                Arguments.of(BMP, bmpUploadPhotoDto())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoDtoParams")
    public void getInputStreamTest(
            String testCase,
            UploadPhotoDto uploadPhotoDto
    ) {
        var result = uploadPhotoDto.getInputStream();

        assertNotNull(result);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoDtoParams")
    public void getFileExtensionTests(
            String extension,
            UploadPhotoDto uploadPhotoDto
    ) {
        var result = uploadPhotoDto.getFileExtension();

        assertEquals(extension, result);
    }
}
