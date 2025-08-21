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

import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequest;
import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequestWithBlankConfirmPassword;
import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequestWithBlankPassword;
import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequestWithInvalidConfirmPassword;
import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequestWithNullConfirmPassword;
import static com.github.jbence1994.webshop.user.ConfirmResetPasswordRequestTestObject.confirmResetPasswordRequestWithNullPassword;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ConfirmResetPasswordValidatorTests {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private ConfirmResetPasswordValidator confirmResetPasswordValidator;

    @BeforeEach
    public void setUp() {
        confirmResetPasswordValidator.initialize(mock(ConfirmResetPassword.class));
    }

    private static Stream<Arguments> isNotValidParams() {
        return Stream.of(
                Arguments.of("Request is null", null),
                Arguments.of("Password is null", confirmResetPasswordRequestWithNullPassword()),
                Arguments.of("Confirm password is null", confirmResetPasswordRequestWithNullConfirmPassword()),
                Arguments.of("Password is blank", confirmResetPasswordRequestWithBlankPassword()),
                Arguments.of("Confirm password is blank", confirmResetPasswordRequestWithBlankConfirmPassword()),
                Arguments.of("Confirm password is invalid", confirmResetPasswordRequestWithInvalidConfirmPassword())
        );
    }

    @Test
    public void isValidTest_HappyPath() {
        var result = confirmResetPasswordValidator.isValid(confirmResetPasswordRequest(), context);

        assertThat(result, is(true));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isNotValidParams")
    public void isValidTest_UnhappyPaths(
            String testCase,
            ConfirmResetPasswordRequest request
    ) {
        var result = confirmResetPasswordValidator.isValid(request, context);

        assertThat(result, is(false));
    }
}
