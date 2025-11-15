package com.github.jbence1994.webshop.auth;

public record LoginResponse(Jwt accessToken, Jwt refreshToken) {
}
