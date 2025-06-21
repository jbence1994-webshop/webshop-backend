package com.github.jbence1994.webshop.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Date;

import static com.github.jbence1994.webshop.auth.UserIdentityTestObject.userIdentity;

public final class ClaimsTestObject {
    public static Claims claims() {
        var calendar = Calendar.getInstance();
        calendar.set(9999, Calendar.DECEMBER, 31, 23, 59, 59);
        var expiration = calendar.getTime();

        return buildClaims(expiration);
    }

    public static Claims expiredClaims() {
        var calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 20, 0, 0, 0);
        Date expiration = calendar.getTime();

        return buildClaims(expiration);
    }

    private static Claims buildClaims(Date expiration) {
        return Jwts.claims()
                .subject(userIdentity().id().toString())
                .add("email", userIdentity().email())
                .add("firstName", userIdentity().firstName())
                .add("middleName", userIdentity().middleName())
                .add("lastName", userIdentity().lastName())
                .add("role", userIdentity().role())
                .expiration(expiration)
                .build();
    }
}
