package com.github.jbence1994.webshop.image;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.PhotoTestConstants.BMP;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.JPEG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.JPG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.PNG;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class FileNameGeneratorImplTests {

    @InjectMocks
    private FileNameGeneratorImpl fileNameGenerator;

    private static Stream<Arguments> uploadPhotoDtoParams() {
        return Stream.of(
                Arguments.of(JPEG),
                Arguments.of(JPG),
                Arguments.of(PNG),
                Arguments.of(BMP)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoDtoParams")
    public void generateFileNameTests(String extension) {
        var regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\\.(?:jpg|jpeg|png|bmp)$";
        var result = fileNameGenerator.generate(extension);

        assertThat(Pattern.matches(regex, result), is(true));
    }
}
