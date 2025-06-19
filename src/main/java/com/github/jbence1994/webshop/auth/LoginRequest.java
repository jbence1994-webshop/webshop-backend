package com.github.jbence1994.webshop.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be not empty.")
    private String email;

    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be not empty.")
    private String password;
}
