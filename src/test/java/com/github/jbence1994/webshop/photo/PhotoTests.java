package com.github.jbence1994.webshop.photo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.BMP;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPEG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.JPG;
import static com.github.jbence1994.webshop.photo.PhotoTestConstants.PNG;
import static com.github.jbence1994.webshop.photo.PhotoTestObject.bmpPhoto;
import static com.github.jbence1994.webshop.photo.PhotoTestObject.jpegPhoto;
import static com.github.jbence1994.webshop.photo.PhotoTestObject.jpgPhoto;
import static com.github.jbence1994.webshop.photo.PhotoTestObject.pngPhoto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhotoTests {
    private static Stream<Arguments> photoParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegPhoto()),
                Arguments.of(JPG, jpgPhoto()),
                Arguments.of(PNG, pngPhoto()),
                Arguments.of(BMP, bmpPhoto())
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("photoParams")
    public void getInputStreamTest(
            String testCase,
            Photo photo
    ) {
        var result = photo.getInputStream();

        assertNotNull(result);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("photoParams")
    public void generateFileNameTests(
            String testCase,
            Photo photo
    ) {
        var result = photo.generateFileName();

        assertTrue(
                Pattern.matches(
                        "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\\.(?:jpg|jpeg|png|bmp)$",
                        result
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("photoParams")
    public void getFileExtensionTests(
            String extension,
            Photo photo
    ) {
        var result = photo.getFileExtension();

        assertEquals(extension, result);
    }
}
