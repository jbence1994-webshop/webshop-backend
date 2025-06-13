package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidatorContext;
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

import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequest;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequestWithBlankConfirmPassword;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequestWithBlankPassword;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequestWithInvalidConfirmPassword;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequestWithNullConfirmPassword;
import static com.github.jbence1994.webshop.user.RegisterUserRequestTestObject.registerUserRequestWithNullPassword;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ConfirmPasswordValidatorTests {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private ConfirmPasswordValidator confirmPasswordValidator;

    @BeforeEach
    public void setUp() {
        confirmPasswordValidator.initialize(mock(ConfirmPassword.class));
    }

    private static Stream<Arguments> isNotValidParams() {
        return Stream.of(
                Arguments.of("Request is null", null),
                Arguments.of("Password is null", registerUserRequestWithNullPassword()),
                Arguments.of("Confirm password is null", registerUserRequestWithNullConfirmPassword()),
                Arguments.of("Password is blank", registerUserRequestWithBlankPassword()),
                Arguments.of("Confirm password is blank", registerUserRequestWithBlankConfirmPassword()),
                Arguments.of("Confirm password is invalid", registerUserRequestWithInvalidConfirmPassword())
        );
    }

    @Test
    public void isValidTest_HappyPath() {
        var result = confirmPasswordValidator.isValid(registerUserRequest(), context);

        assertThat(result, is(true));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isNotValidParams")
    public void isValidTest_UnhappyPaths(
            String testCase,
            RegisterUserRequest request
    ) {
        var result = confirmPasswordValidator.isValid(request, context);

        assertThat(result, is(false));
    }
}
