package com.github.jbence1994.webshop.image;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

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

    private static Stream<Arguments> fileParams() {
        return Stream.of(
                Arguments.of(Named.of("File is not empty", multipartFile()), true),
                Arguments.of(Named.of("File is empty", emptyMultipartFile()), false)
        );
    }

    @BeforeEach
    public void setUp() {
        fileNotEmptyValidator.initialize(mock(FileNotEmpty.class));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("fileParams")
    public void isValidTests(MultipartFile file, boolean expectedResult) {
        var result = fileNotEmptyValidator.isValid(file, context);

        assertThat(result, is(expectedResult));
    }
}
