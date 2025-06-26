package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CreateProfileRequest {

    @NotNull(message = "First name must be provided.")
    @NotBlank(message = "First name must be not empty.")
    private String firstName;

    private String middleName;

    @NotNull(message = "Last name must be provided.")
    @NotBlank(message = "Last name must be not empty.")
    private String lastName;

    @NotNull(message = "Date of Birth must be provided.")
    private LocalDate dateOfBirth;

    private String phoneNumber;
}
