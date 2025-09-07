package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestSanitizer {

    public RegistrationRequest sanitize(RegistrationRequest request) {
        var sanitizedUserDto = sanitize(request.getUser());

        return new RegistrationRequest(sanitizedUserDto);
    }

    private RegistrationRequest.UserDto sanitize(RegistrationRequest.UserDto userDto) {
        var sanitizedEmail = userDto.getEmail().trim();
        var sanitizedPassword = userDto.getPassword().trim();
        var sanitizedConfirmPassword = userDto.getConfirmPassword().trim();
        var sanitizedProfileDto = sanitize(userDto.getProfile());

        return new RegistrationRequest.UserDto(
                sanitizedEmail,
                sanitizedPassword,
                sanitizedConfirmPassword,
                sanitizedProfileDto
        );
    }

    private RegistrationRequest.ProfileDto sanitize(RegistrationRequest.ProfileDto profileDto) {
        var sanitizedFirstName = profileDto.getFirstName().trim();
        var sanitizedMiddleName = profileDto.getMiddleName().trim();
        var sanitizedLastName = profileDto.getLastName().trim();
        var sanitizedPhoneNumber = profileDto.getPhoneNumber().trim();
        var sanitizedAddressDto = sanitize(profileDto.getAddress());

        return new RegistrationRequest.ProfileDto(
                sanitizedFirstName,
                sanitizedMiddleName,
                sanitizedLastName,
                profileDto.getDateOfBirth(),
                sanitizedPhoneNumber,
                sanitizedAddressDto
        );
    }

    private RegistrationRequest.AddressDto sanitize(RegistrationRequest.AddressDto addressDto) {
        var sanitizedAddressLine = addressDto.getAddressLine().trim();
        var sanitizedMunicipality = addressDto.getMunicipality().trim();
        var sanitizedProvince = addressDto.getProvince().trim();
        var sanitizedPostalCode = addressDto.getPostalCode().trim();
        var sanitizedCountry = addressDto.getCountry().trim();

        return new RegistrationRequest.AddressDto(
                sanitizedAddressLine,
                sanitizedMunicipality,
                sanitizedProvince,
                sanitizedPostalCode,
                sanitizedCountry
        );
    }
}
