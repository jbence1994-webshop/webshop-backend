package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class ResetPasswordRequestTestObject {
    public static ResetPasswordRequest resetPasswordRequest() {
        return new ResetPasswordRequest(EMAIL);
    }
}
