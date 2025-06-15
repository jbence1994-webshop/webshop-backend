package com.github.jbence1994.webshop.image;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.BMP;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPEG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PNG;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.bmpUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.jpegUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.jpgUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.pngUploadImage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class UploadImageTests {
    private static Stream<Arguments> uploadImageParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegUploadImage()),
                Arguments.of(JPG, jpgUploadImage()),
                Arguments.of(PNG, pngUploadImage()),
                Arguments.of(BMP, bmpUploadImage())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadImageParams")
    public void getInputStreamTests(
            String testCase,
            UploadImage uploadImage
    ) {
        var result = uploadImage.getInputStream();

        assertThat(result, not(nullValue()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadImageParams")
    public void getFileExtensionTests(
            String extension,
            UploadImage uploadImage
    ) {
        var result = uploadImage.getFileExtension();

        assertThat(result, equalTo(extension));
    }
}
