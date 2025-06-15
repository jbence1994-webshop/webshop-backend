package com.github.jbence1994.webshop.image;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.image.MultipartFileTestObject.emptyMultipartFile;
import static com.github.jbence1994.webshop.image.MultipartFileTestObject.multipartFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    // TODO: Refactor to parameterized test.
    @Test
    public void isValidTest_HappyPath() {
        var result = fileNotEmptyValidator.isValid(multipartFile(), context);

        assertThat(result, is(true));
    }

    @Test
    public void isValidTest_UnhappyPath_FileIsEmpty() {
        var result = fileNotEmptyValidator.isValid(emptyMultipartFile(), context);

        assertThat(result, is(false));
    }
}
