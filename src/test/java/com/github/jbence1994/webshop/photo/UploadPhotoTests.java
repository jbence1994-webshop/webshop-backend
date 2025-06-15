package com.github.jbence1994.webshop.photo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PNG;
import static com.github.jbence1994.webshop.photo.UploadPhotoTestObject.bmpUploadPhoto;
import static com.github.jbence1994.webshop.photo.UploadPhotoTestObject.jpegUploadPhoto;
import static com.github.jbence1994.webshop.photo.UploadPhotoTestObject.jpgUploadPhoto;
import static com.github.jbence1994.webshop.photo.UploadPhotoTestObject.pngUploadPhoto;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UploadPhotoTests {
    private static Stream<Arguments> uploadPhotoParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegUploadPhoto()),
                Arguments.of(JPG, jpgUploadPhoto()),
                Arguments.of(PNG, pngUploadPhoto()),
                Arguments.of(BMP, bmpUploadPhoto())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoParams")
    public void getInputStreamTests(
            String testCase,
            UploadPhoto uploadPhoto
    ) {
        var result = uploadPhoto.getInputStream();

        assertThat(result, not(nullValue()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoParams")
    public void getFileExtensionTests(
            String extension,
            UploadPhoto uploadPhoto
    ) {
        var result = uploadPhoto.getFileExtension();

        assertThat(result, equalTo(extension));
    }
}
