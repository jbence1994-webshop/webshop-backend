package com.github.jbence1994.webshop.user;

public record UserDto(
        Long id,
        String email,
        ProfileDto profile
) {
}
