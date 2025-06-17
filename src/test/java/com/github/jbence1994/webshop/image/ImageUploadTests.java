package com.github.jbence1994.webshop.image;

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
    private static Stream<Arguments> imageUploadParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegImageUpload()),
                Arguments.of(JPG, jpgImageUpload()),
                Arguments.of(PNG, pngImageUpload()),
                Arguments.of(BMP, bmpImageUpload())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("imageUploadParams")
    public void getInputStreamTests(
            String testCase,
            ImageUpload image
    ) {
        var result = image.getInputStream();

        assertThat(result, not(nullValue()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("imageUploadParams")
    public void getFileExtensionTests(
            String extension,
            ImageUpload image
    ) {
        var result = image.getFileExtension();

        assertThat(result, equalTo(extension));
    }
}
