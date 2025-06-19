package com.github.jbence1994.webshop.auth;

import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class LoginRequestTestObject {
    public static LoginRequest loginRequest() {
        return buildLoginRequest(PASSWORD);
    }

    public static LoginRequest invalidLoginRequest() {
        return buildLoginRequest(INVALID_PASSWORD);
    }

    private static LoginRequest buildLoginRequest(String password) {
        return new LoginRequest(EMAIL, password);
    }
}
