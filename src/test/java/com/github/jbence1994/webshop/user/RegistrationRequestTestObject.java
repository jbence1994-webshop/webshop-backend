package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.AddressTestConstants.ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.COUNTRY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.MUNICIPALITY;
import static com.github.jbence1994.webshop.user.AddressTestConstants.POSTAL_CODE;
import static com.github.jbence1994.webshop.user.AddressTestConstants.PROVINCE;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.ProfileTestConstants.PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.UserTestConstants.CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL;
import static com.github.jbence1994.webshop.user.UserTestConstants.INVALID_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;

public final class RegistrationRequestTestObject {
    public static RegistrationRequest registrationRequest() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(PASSWORD, CONFIRM_PASSWORD));
    }

    public static RegistrationRequest notSanitizedRegistrationRequest() {
        return buildRegistrationRequest(UserDtoTestObject.notSanitizedUserDto());
    }

    public static RegistrationRequest registrationRequestWithInvalidConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(PASSWORD, INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithNullUser() {
        return buildRegistrationRequest(null);
    }

    public static RegistrationRequest registrationRequestWithNullPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(null, INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithNullConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(PASSWORD, null));
    }

    public static RegistrationRequest registrationRequestWithBlankPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(" ", INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithBlankConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(PASSWORD, " "));
    }

    private static RegistrationRequest buildRegistrationRequest(RegistrationRequest.UserDto userDto) {
        return new RegistrationRequest(userDto);
    }

    private static final class UserDtoTestObject {
        public static RegistrationRequest.UserDto userDto(
                String password,
                String confirmPassword
        ) {
            return new RegistrationRequest.UserDto(
                    EMAIL,
                    password,
                    confirmPassword,
                    ProfileDtoTestObject.profileDto()
            );
        }

        public static RegistrationRequest.UserDto notSanitizedUserDto() {
            return new RegistrationRequest.UserDto(
                    " " + EMAIL + " ",
                    " " + PASSWORD + " ",
                    " " + CONFIRM_PASSWORD + " ",
                    ProfileDtoTestObject.notSanitizedProfileDto()
            );
        }
    }

    private static final class ProfileDtoTestObject {
        public static RegistrationRequest.ProfileDto profileDto() {
            return new RegistrationRequest.ProfileDto(
                    FIRST_NAME,
                    MIDDLE_NAME,
                    LAST_NAME,
                    DATE_OF_BIRTH,
                    PHONE_NUMBER,
                    AddressDtoTestObject.addressDto()
            );
        }

        public static RegistrationRequest.ProfileDto notSanitizedProfileDto() {
            return new RegistrationRequest.ProfileDto(
                    " " + FIRST_NAME + " ",
                    " " + MIDDLE_NAME + " ",
                    " " + LAST_NAME + " ",
                    DATE_OF_BIRTH,
                    " " + PHONE_NUMBER + " ",
                    AddressDtoTestObject.notSanitizedAddressDto()
            );
        }
    }

    private static final class AddressDtoTestObject {
        public static RegistrationRequest.AddressDto addressDto() {
            return new RegistrationRequest.AddressDto(
                    ADDRESS_LINE,
                    MUNICIPALITY,
                    PROVINCE,
                    POSTAL_CODE,
                    COUNTRY
            );
        }

        public static RegistrationRequest.AddressDto notSanitizedAddressDto() {
            return new RegistrationRequest.AddressDto(
                    " " + ADDRESS_LINE + " ",
                    " " + MUNICIPALITY + " ",
                    " " + PROVINCE + " ",
                    " " + POSTAL_CODE + " ",
                    " " + COUNTRY + " "
            );
        }
    }
}
