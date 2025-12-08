package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_INVALID_CONFIRM_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_NEW_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_PASSWORD;

public final class ChangePasswordRequestTestObject {
    public static ChangePasswordRequest changePasswordRequest() {
        return buildChangePasswordRequest(RAW_NEW_PASSWORD, RAW_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest notSanitizedChangePasswordRequest() {
        return buildChangePasswordRequest(
                " " + RAW_NEW_PASSWORD + " ",
                " " + RAW_CONFIRM_NEW_PASSWORD + " "
        );
    }

    public static ChangePasswordRequest changePasswordRequestWithInvalidConfirmPassword() {
        return buildChangePasswordRequest(RAW_NEW_PASSWORD, RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithNullPassword() {
        return buildChangePasswordRequest(null, RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithNullConfirmPassword() {
        return buildChangePasswordRequest(RAW_NEW_PASSWORD, null);
    }

    public static ChangePasswordRequest changePasswordRequestWithBlankPassword() {
        return buildChangePasswordRequest(" ", RAW_INVALID_CONFIRM_NEW_PASSWORD);
    }

    public static ChangePasswordRequest changePasswordRequestWithBlankConfirmPassword() {
        return buildChangePasswordRequest(RAW_NEW_PASSWORD, " ");
    }

    private static ChangePasswordRequest buildChangePasswordRequest(String newPassword, String confirmNewPassword) {
        return new ChangePasswordRequest(RAW_PASSWORD, newPassword, confirmNewPassword);
    }
}
