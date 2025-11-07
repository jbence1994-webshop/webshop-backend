package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;

public final class ForgotPasswordRequestTestObject {
    public static ForgotPasswordRequest forgotPasswordRequest() {
        return buildForgotPasswordRequest(EMAIL_1);
    }

    public static ForgotPasswordRequest notSanitizedForgotPasswordRequest() {
        return buildForgotPasswordRequest(" " + EMAIL_1 + " ");
    }

    private static ForgotPasswordRequest buildForgotPasswordRequest(String email) {
        return new ForgotPasswordRequest(email);
    }
}
