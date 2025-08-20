package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class ChangePasswordRequestTestObject {
    public static ChangePasswordRequest changePasswordRequest() {
        return buildChangePasswordRequest(NEW_PASSWORD, CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithInvalidConfirmPassword() {
        return buildChangePasswordRequest(NEW_PASSWORD, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithNullPassword() {
        return buildChangePasswordRequest(null, INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithNullConfirmPassword() {
        return buildChangePasswordRequest(NEW_PASSWORD, null);
    }

    public static ChangePasswordRequest changePasswordRequestWithBlankPassword() {
        return buildChangePasswordRequest(" ", INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithBlankConfirmPassword() {
        return buildChangePasswordRequest(NEW_PASSWORD, " ");
    }

    private static ChangePasswordRequest buildChangePasswordRequest(String newPassword, String confirmNewPassword) {
        return new ChangePasswordRequest(PASSWORD, newPassword, confirmNewPassword);
    }
}
