package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestSanitizer {

    public RegistrationRequest sanitize(RegistrationRequest request) {
        return new RegistrationRequest(sanitize(request.user()));
    }

    private RegistrationRequest.UserDto sanitize(RegistrationRequest.UserDto userDto) {
        return new RegistrationRequest.UserDto(
                userDto.email().trim(),
                userDto.password().trim(),
                userDto.confirmPassword().trim(),
                sanitize(userDto.profile())
        );
    }

    private RegistrationRequest.ProfileDto sanitize(RegistrationRequest.ProfileDto profileDto) {
        return new RegistrationRequest.ProfileDto(
                profileDto.firstName().trim(),
                profileDto.middleName() != null ? profileDto.middleName().trim() : null,
                profileDto.lastName().trim(),
                profileDto.dateOfBirth(),
                profileDto.phoneNumber().trim(),
                sanitize(profileDto.address())
        );
    }

    private RegistrationRequest.AddressDto sanitize(RegistrationRequest.AddressDto addressDto) {
        return new RegistrationRequest.AddressDto(
                addressDto.addressLine().trim(),
                addressDto.municipality().trim(),
                addressDto.province().trim(),
                addressDto.postalCode().trim(),
                addressDto.country().trim()
        );
    }
}
