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

import static com.github.jbence1994.webshop.image.PhotoTestConstants.ALLOWED_FILE_EXTENSIONS;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.BMP;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.JPEG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.JPG;
import static com.github.jbence1994.webshop.image.PhotoTestConstants.PNG;
import static com.github.jbence1994.webshop.image.UploadPhotoTestObject.bmpUploadPhoto;
import static com.github.jbence1994.webshop.image.UploadPhotoTestObject.jpegUploadPhoto;
import static com.github.jbence1994.webshop.image.UploadPhotoTestObject.jpgUploadPhoto;
import static com.github.jbence1994.webshop.image.UploadPhotoTestObject.pngUploadPhoto;
import static com.github.jbence1994.webshop.image.UploadPhotoTestObject.tiffUploadPhoto;
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

    private static Stream<Arguments> uploadPhotoParams() {
        return Stream.of(
                Arguments.of(JPEG, jpegUploadPhoto()),
                Arguments.of(JPG, jpgUploadPhoto()),
                Arguments.of(PNG, pngUploadPhoto()),
                Arguments.of(BMP, bmpUploadPhoto())
        );
    }

    @BeforeEach
    public void setUp() {
        when(fileExtensionsConfig.getAllowedFileExtensions()).thenReturn(ALLOWED_FILE_EXTENSIONS);
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("uploadPhotoParams")
    public void validateTests_HappyPath(
            String testCase,
            UploadPhoto uploadPhoto
    ) {
        assertDoesNotThrow(() -> fileExtensionValidator.validate(uploadPhoto));
    }

    @Test
    public void validateTest_UnhappyPath_InvalidFileExtensionException() {
        var result = assertThrows(
                InvalidFileExtensionException.class,
                () -> fileExtensionValidator.validate(tiffUploadPhoto()));

        assertThat(result.getMessage(), equalTo("Invalid file extension: .tiff"));
    }
}
