package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ConfirmPassword
public class RegisterUserRequest {

    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be not empty.")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be not empty.")
    @Size(min = 8, max = 12, message = "Password must be between 8 to 12 characters long.")
    private String password;

    @NotNull(message = "Confirm password must be provided.")
    @NotBlank(message = "Confirm password must be not empty.")
    private String confirmPassword;
}
