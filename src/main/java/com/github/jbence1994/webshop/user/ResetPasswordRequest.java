package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResetPasswordRequest {

    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be not empty.")
    @Email(message = "Email must be valid")
    private String email;
}
