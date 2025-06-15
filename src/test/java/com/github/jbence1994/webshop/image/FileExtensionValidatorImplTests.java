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
import static com.github.jbence1994.webshop.image.UploadImageTestObject.bmpUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.jpegUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.jpgUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.pngUploadImage;
import static com.github.jbence1994.webshop.image.UploadImageTestObject.tiffUploadImage;
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
                Arguments.of(JPEG, jpegUploadImage()),
                Arguments.of(JPG, jpgUploadImage()),
                Arguments.of(PNG, pngUploadImage()),
                Arguments.of(BMP, bmpUploadImage())
        );
    }

    @BeforeEach
    public void setUp() {
        when(fileExtensionsConfig.getAllowedFileExtensions()).thenReturn(ALLOWED_FILE_EXTENSIONS);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadImageParams")
    public void validateTests_HappyPath(
            String testCase,
            UploadImage uploadImage
    ) {
        assertDoesNotThrow(() -> fileExtensionValidator.validate(uploadImage));
    }

    @Test
    public void validateTest_UnhappyPath_InvalidFileExtensionException() {
        var result = assertThrows(
                InvalidFileExtensionException.class,
                () -> fileExtensionValidator.validate(tiffUploadImage()));

        assertThat(result.getMessage(), equalTo("Invalid file extension: .tiff"));
    }
}
