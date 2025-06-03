package com.github.jbence1994.webshop.photo;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.photo.MultipartFileTestObject.emptyMultipartFile;
import static com.github.jbence1994.webshop.photo.MultipartFileTestObject.multipartFile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class FileNotEmptyValidatorTests {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private FileNotEmptyValidator fileNotEmptyValidator;

    @BeforeEach
    public void setUp() {
        fileNotEmptyValidator.initialize(mock(FileNotEmpty.class));
    }

    @Test
    public void isValidTest_HappyPath() {
        var result = fileNotEmptyValidator.isValid(multipartFile(), context);

        assertTrue(result);
    }

    @Test
    public void isValidTest_UnhappyPath_FileIsEmpty() {
        var result = fileNotEmptyValidator.isValid(emptyMultipartFile(), context);

        assertFalse(result);
    }
}
