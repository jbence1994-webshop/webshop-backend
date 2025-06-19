package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;

public interface JwtService {
    Jwt generateAccessToken(UserIdentity identity);

    Jwt generateRefreshToken(UserIdentity identity);

    Jwt parseToken(String token);

    Long getUserIdFromToken(String token);

    Role getRoleFromToken(String token);
}
