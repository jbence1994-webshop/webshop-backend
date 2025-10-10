package com.github.jbence1994.webshop.user;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequest;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequestWithBlankConfirmPassword;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequestWithBlankPassword;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequestWithInvalidConfirmPassword;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequestWithNullConfirmPassword;
import static com.github.jbence1994.webshop.user.ResetPasswordRequestTestObject.resetPasswordRequestWithNullPassword;
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
                Arguments.of(Named.of("Request is null", null)),
                Arguments.of(Named.of("Password is null", resetPasswordRequestWithNullPassword())),
                Arguments.of(Named.of("Confirm password is null", resetPasswordRequestWithNullConfirmPassword())),
                Arguments.of(Named.of("Password is blank", resetPasswordRequestWithBlankPassword())),
                Arguments.of(Named.of("Confirm password is blank", resetPasswordRequestWithBlankConfirmPassword())),
                Arguments.of(Named.of("Confirm password is invalid", resetPasswordRequestWithInvalidConfirmPassword()))
        );
    }

    @Test
    public void isValidTest_HappyPath() {
        var result = confirmResetPasswordValidator.isValid(resetPasswordRequest(), context);

        assertThat(result, is(true));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isNotValidParams")
    public void isValidTest_UnhappyPaths(ResetPasswordRequest request) {
        var result = confirmResetPasswordValidator.isValid(request, context);

        assertThat(result, is(false));
    }
}
