package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;

public final class ForgotPasswordRequestTestObject {
    public static ForgotPasswordRequest forgotPasswordRequest() {
        return new ForgotPasswordRequest(EMAIL);
    }
}
