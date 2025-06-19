package com.github.jbence1994.webshop.auth;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import static com.github.jbence1994.webshop.auth.AuthTestConstants.JWT_SECRET_KEY;

public final class SecretKeyTestObject {
    public static SecretKey secretKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes());
    }
}
