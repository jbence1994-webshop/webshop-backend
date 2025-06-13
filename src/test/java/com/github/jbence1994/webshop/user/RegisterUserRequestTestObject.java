package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class RegisterUserRequestTestObject {
    public static RegisterUserRequest registerUserRequest() {
        return buildRegisterUserRequest(PASSWORD, CONFIRM_PASSWORD);
    }

    public static RegisterUserRequest registerUserRequestWithInvalidConfirmPassword() {
        return buildRegisterUserRequest(PASSWORD, INVALID_CONFIRM_PASSWORD);
    }

    public static RegisterUserRequest registerUserRequestWithNullPassword() {
        return buildRegisterUserRequest(null, INVALID_CONFIRM_PASSWORD);
    }

    public static RegisterUserRequest registerUserRequestWithNullConfirmPassword() {
        return buildRegisterUserRequest(PASSWORD, null);
    }

    public static RegisterUserRequest registerUserRequestWithBlankPassword() {
        return buildRegisterUserRequest(" ", INVALID_CONFIRM_PASSWORD);
    }

    public static RegisterUserRequest registerUserRequestWithBlankConfirmPassword() {
        return buildRegisterUserRequest(PASSWORD, " ");
    }

    private static RegisterUserRequest buildRegisterUserRequest(String password, String confirmPassword) {
        return new RegisterUserRequest(EMAIL, password, confirmPassword);
    }
}
