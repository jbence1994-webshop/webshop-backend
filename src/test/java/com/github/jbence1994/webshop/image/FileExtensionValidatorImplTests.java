package com.github.jbence1994.webshop.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.image.ImageTestConstants.ALLOWED_FILE_EXTENSIONS;
import static com.github.jbence1994.webshop.image.ImageTestConstants.BMP;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPEG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.JPG;
import static com.github.jbence1994.webshop.image.ImageTestConstants.PNG;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.bmpImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.jpegImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.pngImageUpload;
import static com.github.jbence1994.webshop.image.ImageUploadTestObject.tiffImageUpload;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileExtensionValidatorImplTests {

    @Mock
    private FileExtensionsConfig fileExtensionsConfig;

    @InjectMocks
    private FileExtensionValidatorImpl fileExtensionValidator;

    private static Stream<Arguments> uploadImageParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegImageUpload()),
                Arguments.of(JPG, jpegImageUpload()),
                Arguments.of(PNG, pngImageUpload()),
                Arguments.of(BMP, bmpImageUpload())
        );
    }

    @BeforeEach
    public void setUp() {
        when(fileExtensionsConfig.allowedFileExtensions()).thenReturn(ALLOWED_FILE_EXTENSIONS);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadImageParams")
    public void validateTests_HappyPath(
            String testCase,
            ImageUpload image
    ) {
        assertDoesNotThrow(() -> fileExtensionValidator.validate(image));
    }

    @Test
    public void validateTest_UnhappyPath_InvalidFileExtensionException() {
        var result = assertThrows(
                InvalidFileExtensionException.class,
                () -> fileExtensionValidator.validate(tiffImageUpload()));

        assertThat(result.getMessage(), equalTo("Invalid file extension: .tiff"));
    }
}
