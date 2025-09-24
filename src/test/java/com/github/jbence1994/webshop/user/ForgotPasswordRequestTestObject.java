package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class ForgotPasswordRequestTestObject {
    public static ForgotPasswordRequest forgotPasswordRequest() {
        return buildForgotPasswordRequest(EMAIL);
    }

    public static ForgotPasswordRequest notSanitizedForgotPasswordRequest() {
        return buildForgotPasswordRequest(" " + EMAIL + " ");
    }

    private static ForgotPasswordRequest buildForgotPasswordRequest(String email) {
        return new ForgotPasswordRequest(email);
    }
}
