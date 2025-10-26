package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ForgotPasswordRequest(

        @NotNull(message = "Email must be provided.")
        @NotBlank(message = "Email must be not empty.")
        @Email(message = "Email must be valid.")
        String email
) {
}
