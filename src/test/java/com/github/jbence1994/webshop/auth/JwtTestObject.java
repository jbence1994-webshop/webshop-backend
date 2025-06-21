package com.github.jbence1994.webshop.auth;

import io.jsonwebtoken.Claims;

import static com.github.jbence1994.webshop.auth.ClaimsTestObject.claims;
import static com.github.jbence1994.webshop.auth.ClaimsTestObject.expiredClaims;
import static com.github.jbence1994.webshop.auth.SecretKeyTestObject.secretKey;

public final class JwtTestObject {

    public static Jwt accessToken() {
        return buildJwt(claims());
    }

    public static Jwt refreshToken() {
        return buildJwt(claims());
    }

    public static Jwt expiredAccessToken() {
        return new Jwt(expiredClaims(), secretKey());
    }

    private static Jwt buildJwt(Claims claims) {
        return new Jwt(claims, secretKey());
    }
}
