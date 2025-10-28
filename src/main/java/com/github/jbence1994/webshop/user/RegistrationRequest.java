package com.github.jbence1994.webshop.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@ConfirmUserPassword
public record RegistrationRequest(

        @Valid
        UserDto user
) {
    public record UserDto(

            @NotNull(message = "Email must be provided.")
            @NotBlank(message = "Email must be not empty.")
            @Email(message = "Email must be valid.")
            String email,

            @NotNull(message = "Password must be provided.")
            @NotBlank(message = "Password must be not empty.")
            @Size(min = 8, max = 12, message = "Password must be between 8 to 12 characters long.")
            String password,

            @NotNull(message = "Confirm password must be provided.")
            @NotBlank(message = "Confirm password must be not empty.")
            String confirmPassword,

            @Valid
            ProfileDto profile
    ) {
    }

    public record ProfileDto(

            @NotNull(message = "First name must be provided.")
            @NotBlank(message = "First name must be not empty.")
            String firstName,

            String middleName,

            @NotNull(message = "Last name must be provided.")
            @NotBlank(message = "Last name must be not empty.")
            String lastName,

            @NotNull(message = "Date of Birth must be provided.")
            LocalDate dateOfBirth,

            String phoneNumber,

            @Valid
            AddressDto address
    ) {
    }

    public record AddressDto(

            @NotNull(message = "Address line must be provided.")
            @NotBlank(message = "Address line must be not empty.")
            String addressLine,

            @NotNull(message = "Municipality must be provided.")
            @NotBlank(message = "Municipality must be not empty.")
            String municipality,

            @NotNull(message = "Province must be provided.")
            @NotBlank(message = "Province must be not empty.")
            String province,

            @NotNull(message = "Postal code must be provided.")
            @NotBlank(message = "Postal code must be not empty.")
            String postalCode,

            @NotNull(message = "Country must be provided.")
            @NotBlank(message = "Country must be not empty.")
            String country
    ) {
    }
}
