package com.github.jbence1994.webshop.auth;

import static com.github.jbence1994.webshop.auth.ClaimsTestObject.claims;
import static com.github.jbence1994.webshop.auth.SecretKeyTestObject.secretKey;

public final class JwtTestObject {

    public static Jwt accessToken() {
        return new Jwt(claims(), secretKey());
    }

    public static Jwt refreshToken() {
        return new Jwt(claims(), secretKey());
    }
}
