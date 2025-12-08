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
                userDto.firstName().trim(),
                userDto.middleName() != null ? userDto.middleName().trim() : null,
                userDto.lastName().trim(),
                userDto.dateOfBirth(),
                userDto.phoneNumber().trim(),
                sanitize(userDto.billingAddress()),
                sanitize(userDto.shippingAddress())
        );
    }

    private RegistrationRequest.BillingAddressDto sanitize(RegistrationRequest.BillingAddressDto billingAddressDto) {
        return new RegistrationRequest.BillingAddressDto(
                billingAddressDto.addressLine().trim(),
                billingAddressDto.municipality().trim(),
                billingAddressDto.province().trim(),
                billingAddressDto.postalCode().trim(),
                billingAddressDto.country().trim()
        );
    }

    private RegistrationRequest.ShippingAddressDto sanitize(RegistrationRequest.ShippingAddressDto shippingAddressDto) {
        return new RegistrationRequest.ShippingAddressDto(
                shippingAddressDto.addressLine().trim(),
                shippingAddressDto.municipality().trim(),
                shippingAddressDto.province().trim(),
                shippingAddressDto.postalCode().trim(),
                shippingAddressDto.country().trim()
        );
    }
}
