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

import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequest;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequestWithBlankConfirmPassword;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequestWithBlankPassword;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequestWithInvalidConfirmPassword;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequestWithNullConfirmPassword;
import static com.github.jbence1994.webshop.user.ChangePasswordRequestTestObject.changePasswordRequestWithNullPassword;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ConfirmNewPasswordValidatorTests {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private ConfirmNewPasswordValidator confirmNewPasswordValidator;

    @BeforeEach
    public void setUp() {
        confirmNewPasswordValidator.initialize(mock(ConfirmNewPassword.class));
    }

    private static Stream<Arguments> isNotValidParams() {
        return Stream.of(
                Arguments.of("Request is null", null),
                Arguments.of("Password is null", changePasswordRequestWithNullPassword()),
                Arguments.of("Confirm password is null", changePasswordRequestWithNullConfirmPassword()),
                Arguments.of("Password is blank", changePasswordRequestWithBlankPassword()),
                Arguments.of("Confirm password is blank", changePasswordRequestWithBlankConfirmPassword()),
                Arguments.of("Confirm password is invalid", changePasswordRequestWithInvalidConfirmPassword())
        );
    }

    @Test
    public void isValidTest_HappyPath() {
        var result = confirmNewPasswordValidator.isValid(changePasswordRequest(), context);

        assertThat(result, is(true));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("isNotValidParams")
    public void isValidTest_UnhappyPaths(
            String testCase,
            ChangePasswordRequest request
    ) {
        var result = confirmNewPasswordValidator.isValid(request, context);

        assertThat(result, is(false));
    }
}
