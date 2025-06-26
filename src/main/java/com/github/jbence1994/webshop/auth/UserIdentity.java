package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.Role;

public record UserIdentity(Long id, String email, String firstName, String middleName, String lastName, Role role) {
}
