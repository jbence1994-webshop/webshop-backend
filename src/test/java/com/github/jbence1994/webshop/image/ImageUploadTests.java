package com.github.jbence1994.webshop.image;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.BMP;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPEG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PNG;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.bmpImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.jpegImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.jpgImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.pngImageUpload;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class ImageUploadTests {

    private static Stream<Arguments> getInputStreamTestParams() {
        return Stream.of(
                Arguments.of(Named.of(JPEG, jpegImageUpload())),
                Arguments.of(Named.of(JPG, jpgImageUpload())),
                Arguments.of(Named.of(PNG, pngImageUpload())),
                Arguments.of(Named.of(BMP, bmpImageUpload()))
        );
    }

    private static Stream<Arguments> getFileExtensionTestParams() {
        return Stream.of(
                Arguments.of(Named.of(JPEG, JPEG), jpegImageUpload()),
                Arguments.of(Named.of(JPG, JPG), jpgImageUpload()),
                Arguments.of(Named.of(PNG, PNG), pngImageUpload()),
                Arguments.of(Named.of(BMP, BMP), bmpImageUpload())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("getInputStreamTestParams")
    public void getInputStreamTests(ImageUpload image) {
        var result = image.getInputStream();

        assertThat(result, not(nullValue()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("getFileExtensionTestParams")
    public void getFileExtensionTests(String extension, ImageUpload image) {
        var result = image.getFileExtension();

        assertThat(result, equalTo(extension));
    }
}
