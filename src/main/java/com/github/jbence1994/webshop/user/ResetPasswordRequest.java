package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ConfirmResetPassword
public record ResetPasswordRequest(

        @NotNull(message = "Temporary password must be provided.")
        @NotBlank(message = "Temporary password must be not empty.")
        String temporaryPassword,

        @NotNull(message = "New password must be provided.")
        @NotBlank(message = "New password must be not empty.")
        @Size(min = 8, max = 12, message = "Password must be between 8 to 12 characters long.")
        String newPassword,

        @NotNull(message = "Confirm password must be provided.")
        @NotBlank(message = "Confirm password must be not empty.")
        String confirmNewPassword
) {
}
