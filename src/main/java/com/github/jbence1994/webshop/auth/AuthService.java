package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    Jwt refreshAccessToken(String refreshToken);

    User getCurrentUser();
}
