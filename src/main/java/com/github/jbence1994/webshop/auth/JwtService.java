package com.github.jbence1994.webshop.auth;

public interface JwtService {
    Jwt generateAccessToken(UserIdentity identity);

    Jwt generateRefreshToken(UserIdentity identity);

    Jwt parseToken(String token);
}
