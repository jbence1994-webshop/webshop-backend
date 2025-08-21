package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.TemporaryPasswordTestConstants.TEMPORARY_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;

public final class ConfirmResetPasswordRequestTestObject {
    public static ConfirmResetPasswordRequest confirmResetPasswordRequest() {
        return buildConfirmResetPasswordRequest(NEW_PASSWORD, CONFIRM_NEW_PASSWORD);
    }

    public static ConfirmResetPasswordRequest confirmResetPasswordRequestWithInvalidConfirmPassword() {
        return buildConfirmResetPasswordRequest(NEW_PASSWORD, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ConfirmResetPasswordRequest confirmResetPasswordRequestWithNullPassword() {
        return buildConfirmResetPasswordRequest(null, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ConfirmResetPasswordRequest confirmResetPasswordRequestWithNullConfirmPassword() {
        return buildConfirmResetPasswordRequest(NEW_PASSWORD, null);
    }

    public static ConfirmResetPasswordRequest confirmResetPasswordRequestWithBlankPassword() {
        return buildConfirmResetPasswordRequest(" ", INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ConfirmResetPasswordRequest confirmResetPasswordRequestWithBlankConfirmPassword() {
        return buildConfirmResetPasswordRequest(NEW_PASSWORD, " ");
    }

    private static ConfirmResetPasswordRequest buildConfirmResetPasswordRequest(String newPassword, String confirmNewPassword) {
        return new ConfirmResetPasswordRequest(TEMPORARY_PASSWORD, newPassword, confirmNewPassword);
    }
}
