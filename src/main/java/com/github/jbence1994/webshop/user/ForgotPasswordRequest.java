package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ForgotPasswordRequest {

    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be not empty.")
    @Email(message = "Email must be valid.")
    private String email;
}
