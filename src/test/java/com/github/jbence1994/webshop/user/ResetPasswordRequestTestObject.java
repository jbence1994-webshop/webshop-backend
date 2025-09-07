package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;

public final class ResetPasswordRequestTestObject {
    public static ResetPasswordRequest resetPasswordRequest() {
        return buildResetPasswordRequest(NEW_PASSWORD, CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest notSanitizedResetPasswordRequest() {
        return buildResetPasswordRequest(
                " " + NEW_PASSWORD + " ",
                " " + CONFIRM_NEW_PASSWORD + " "
        );
    }

    public static ResetPasswordRequest resetPasswordRequestWithInvalidConfirmPassword() {
        return buildResetPasswordRequest(NEW_PASSWORD, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithNullPassword() {
        return buildResetPasswordRequest(null, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithNullConfirmPassword() {
        return buildResetPasswordRequest(NEW_PASSWORD, null);
    }

    public static ResetPasswordRequest resetPasswordRequestWithBlankPassword() {
        return buildResetPasswordRequest(" ", INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithBlankConfirmPassword() {
        return buildResetPasswordRequest(NEW_PASSWORD, " ");
    }

    private static ResetPasswordRequest buildResetPasswordRequest(String newPassword, String confirmNewPassword) {
        return new ResetPasswordRequest(TEMPORARY_PASSWORD, newPassword, confirmNewPassword);
    }
}
