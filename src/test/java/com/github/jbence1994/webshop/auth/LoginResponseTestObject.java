package com.github.jbence1994.webshop.auth;

import static com.github.jbence1994.webshop.auth.JwtTestObject.accessToken;
import static com.github.jbence1994.webshop.auth.JwtTestObject.refreshToken;

public final class LoginResponseTestObject {
    public static LoginResponse loginResponse() {
        return new LoginResponse(accessToken(), refreshToken());
    }
}
