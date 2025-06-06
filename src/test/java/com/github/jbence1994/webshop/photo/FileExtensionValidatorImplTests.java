package com.github.jbence1994.webshop.photo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.photo.PhotoTestConstants.ALLOWED_FILE_EXTENSIONS;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.jpegUploadPhotoDto;
import static com.github.jbence1994.webshop.photo.UploadPhotoDtoTestObject.tiffUploadPhotoDto;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileExtensionValidatorImplTests {

    @Mock
    private FileExtensionsConfig fileExtensionsConfig;

    @InjectMocks
    private FileExtensionValidatorImpl fileExtensionValidator;

    @BeforeEach
    public void setUp() {
        when(fileExtensionsConfig.getAllowedFileExtensions()).thenReturn(ALLOWED_FILE_EXTENSIONS);
    }

    @Test
    public void validateTest_HappyPath() {
        assertDoesNotThrow(
                () -> fileExtensionValidator.validate(jpegUploadPhotoDto())
        );
    }

    @Test
    public void validateTest_UnhappyPath_InvalidFileExtensionException() {
        var result = assertThrows(
                InvalidFileExtensionException.class,
                () -> fileExtensionValidator.validate(tiffUploadPhotoDto()));

        assertEquals("Invalid file extension: .tiff", result.getMessage());
    }
}
