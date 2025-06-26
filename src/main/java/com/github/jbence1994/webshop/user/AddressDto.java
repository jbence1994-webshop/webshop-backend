package com.github.jbence1994.webshop.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressDto {

    @NotNull(message = "Address line must be provided.")
    @NotBlank(message = "Address line must be not empty.")
    private String addressLine;

    @NotNull(message = "Municipality must be provided.")
    @NotBlank(message = "Municipality must be not empty.")
    private String municipality;

    @NotNull(message = "Province must be provided.")
    @NotBlank(message = "Province must be not empty.")
    private String province;

    @NotNull(message = "Postal code must be provided.")
    @NotBlank(message = "Postal code must be not empty.")
    private String postalCode;

    @NotNull(message = "Country must be provided.")
    @NotBlank(message = "Country must be not empty.")
    private String country;
}
