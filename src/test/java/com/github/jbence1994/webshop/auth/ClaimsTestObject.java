package com.github.jbence1994.webshop.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Calendar;
import java.util.Date;

import static com.github.jbence1994.webshop.auth.UserIdentityTestObject.userIdentity;

public final class ClaimsTestObject {
    public static Claims claims() {
        return Jwts.claims()
                .subject(userIdentity().id().toString())
                .add("email", userIdentity().email())
                .add("firstName", userIdentity().firstName())
                .add("middleName", userIdentity().middleName())
                .add("lastName", userIdentity().lastName())
                .expiration(new Date(9999, Calendar.DECEMBER, 31, 23, 59, 59))
                .build();
    }
}
