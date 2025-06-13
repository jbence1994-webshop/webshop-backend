package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ConfirmNewPassword
public class ChangePasswordRequest {

    @NotBlank(message = "Password must be not empty.")
    private String oldPassword;

    @NotBlank(message = "New password must be not empty.")
    @Size(min = 8, max = 12, message = "Password must be between 8 to 12 characters long.")
    private String newPassword;

    @NotBlank(message = "Confirm password must be not empty.")
    private String confirmNewPassword;
}
