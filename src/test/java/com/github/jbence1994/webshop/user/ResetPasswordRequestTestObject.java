package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_INVALID_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;

public final class ResetPasswordRequestTestObject {
    public static ResetPasswordRequest resetPasswordRequest() {
        return buildResetPasswordRequest(RAW_NEW_PASSWORD, RAW_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest notSanitizedResetPasswordRequest() {
        return buildResetPasswordRequest(
                " " + RAW_NEW_PASSWORD + " ",
                " " + RAW_CONFIRM_NEW_PASSWORD + " "
        );
    }

    public static ResetPasswordRequest resetPasswordRequestWithInvalidConfirmPassword() {
        return buildResetPasswordRequest(RAW_NEW_PASSWORD, RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithNullPassword() {
        return buildResetPasswordRequest(null, RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithNullConfirmPassword() {
        return buildResetPasswordRequest(RAW_NEW_PASSWORD, null);
    }

    public static ResetPasswordRequest resetPasswordRequestWithBlankPassword() {
        return buildResetPasswordRequest(" ", RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ResetPasswordRequest resetPasswordRequestWithBlankConfirmPassword() {
        return buildResetPasswordRequest(RAW_NEW_PASSWORD, " ");
    }

    private static ResetPasswordRequest buildResetPasswordRequest(String newPassword, String confirmNewPassword) {
        return new ResetPasswordRequest(TEMPORARY_PASSWORD, newPassword, confirmNewPassword);
    }
}
