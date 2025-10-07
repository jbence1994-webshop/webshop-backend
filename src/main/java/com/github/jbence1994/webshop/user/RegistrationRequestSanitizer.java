package com.github.jbence1994.webshop.user;

import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestSanitizer {

    public RegistrationRequest sanitize(RegistrationRequest request) {
        return new RegistrationRequest(sanitize(request.getUser()));
    }

    private RegistrationRequest.UserDto sanitize(RegistrationRequest.UserDto userDto) {
        return new RegistrationRequest.UserDto(
                userDto.getEmail().trim(),
                userDto.getPassword().trim(),
                userDto.getConfirmPassword().trim(),
                sanitize(userDto.getProfile())
        );
    }

    private RegistrationRequest.ProfileDto sanitize(RegistrationRequest.ProfileDto profileDto) {
        return new RegistrationRequest.ProfileDto(
                profileDto.getFirstName().trim(),
                profileDto.getMiddleName() != null ? profileDto.getMiddleName().trim() : null,
                profileDto.getLastName().trim(),
                profileDto.getDateOfBirth(),
                profileDto.getPhoneNumber().trim(),
                sanitize(profileDto.getAddress())
        );
    }

    private RegistrationRequest.AddressDto sanitize(RegistrationRequest.AddressDto addressDto) {
        return new RegistrationRequest.AddressDto(
                addressDto.getAddressLine().trim(),
                addressDto.getMunicipality().trim(),
                addressDto.getProvince().trim(),
                addressDto.getPostalCode().trim(),
                addressDto.getCountry().trim()
        );
    }
}
