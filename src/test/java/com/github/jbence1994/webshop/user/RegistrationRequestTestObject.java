package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_FIRST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_LAST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_INVALID_CONFIRM_PASSWORD;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_PASSWORD;

public final class RegistrationRequestTestObject {
    public static RegistrationRequest registrationRequest() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(RAW_PASSWORD, RAW_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest notSanitizedRegistrationRequest() {
        return buildRegistrationRequest(UserDtoTestObject.notSanitizedUserDto());
    }

    public static RegistrationRequest notSanitizedRegistrationRequestWithNullMiddleName() {
        return buildRegistrationRequest(UserDtoTestObject.notSanitizedUserDtoWithNullMiddleName());
    }

    public static RegistrationRequest registrationRequestWithInvalidConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(RAW_PASSWORD, RAW_INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithNullUser() {
        return buildRegistrationRequest(null);
    }

    public static RegistrationRequest registrationRequestWithNullPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(null, RAW_INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithNullConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(RAW_PASSWORD, null));
    }

    public static RegistrationRequest registrationRequestWithBlankPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(" ", RAW_INVALID_CONFIRM_PASSWORD));
    }

    public static RegistrationRequest registrationRequestWithBlankConfirmPassword() {
        return buildRegistrationRequest(UserDtoTestObject.userDto(RAW_PASSWORD, " "));
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
                    DECRYPTED_EMAIL_1,
                    password,
                    confirmPassword,
                    DECRYPTED_FIRST_NAME,
                    DECRYPTED_MIDDLE_NAME,
                    DECRYPTED_LAST_NAME,
                    DECRYPTED_DATE_OF_BIRTH,
                    DECRYPTED_PHONE_NUMBER,
                    BillingAddressDtoTestObject.billingAddressDto(),
                    ShippingAddressDtoTestObject.shippingAddressDto()
            );
        }

        public static RegistrationRequest.UserDto notSanitizedUserDto() {
            return new RegistrationRequest.UserDto(
                    " " + DECRYPTED_EMAIL_1 + " ",
                    " " + RAW_PASSWORD + " ",
                    " " + RAW_CONFIRM_PASSWORD + " ",
                    " " + DECRYPTED_FIRST_NAME + " ",
                    " " + DECRYPTED_MIDDLE_NAME + " ",
                    " " + DECRYPTED_LAST_NAME + " ",
                    DECRYPTED_DATE_OF_BIRTH,
                    " " + DECRYPTED_PHONE_NUMBER + " ",
                    BillingAddressDtoTestObject.notSanitizedBillingAddressDto(),
                    ShippingAddressDtoTestObject.notSanitizedShippingAddressDto()
            );
        }

        public static RegistrationRequest.UserDto notSanitizedUserDtoWithNullMiddleName() {
            return new RegistrationRequest.UserDto(
                    " " + DECRYPTED_EMAIL_1 + " ",
                    " " + RAW_PASSWORD + " ",
                    " " + RAW_CONFIRM_PASSWORD + " ",
                    " " + DECRYPTED_FIRST_NAME + " ",
                    null,
                    " " + DECRYPTED_LAST_NAME + " ",
                    DECRYPTED_DATE_OF_BIRTH,
                    " " + DECRYPTED_PHONE_NUMBER + " ",
                    BillingAddressDtoTestObject.notSanitizedBillingAddressDto(),
                    ShippingAddressDtoTestObject.notSanitizedShippingAddressDto()
            );
        }
    }

    private static final class BillingAddressDtoTestObject {
        public static RegistrationRequest.BillingAddressDto billingAddressDto() {
            return new RegistrationRequest.BillingAddressDto(
                    DECRYPTED_ADDRESS_LINE,
                    DECRYPTED_MUNICIPALITY,
                    DECRYPTED_PROVINCE,
                    DECRYPTED_POSTAL_CODE,
                    DECRYPTED_COUNTRY
            );
        }

        public static RegistrationRequest.BillingAddressDto notSanitizedBillingAddressDto() {
            return new RegistrationRequest.BillingAddressDto(
                    " " + DECRYPTED_ADDRESS_LINE + " ",
                    " " + DECRYPTED_MUNICIPALITY + " ",
                    " " + DECRYPTED_PROVINCE + " ",
                    " " + DECRYPTED_POSTAL_CODE + " ",
                    " " + DECRYPTED_COUNTRY + " "
            );
        }
    }

    private static final class ShippingAddressDtoTestObject {
        public static RegistrationRequest.ShippingAddressDto shippingAddressDto() {
            return new RegistrationRequest.ShippingAddressDto(
                    DECRYPTED_ADDRESS_LINE,
                    DECRYPTED_MUNICIPALITY,
                    DECRYPTED_PROVINCE,
                    DECRYPTED_POSTAL_CODE,
                    DECRYPTED_COUNTRY
            );
        }

        public static RegistrationRequest.ShippingAddressDto notSanitizedShippingAddressDto() {
            return new RegistrationRequest.ShippingAddressDto(
                    " " + DECRYPTED_ADDRESS_LINE + " ",
                    " " + DECRYPTED_MUNICIPALITY + " ",
                    " " + DECRYPTED_PROVINCE + " ",
                    " " + DECRYPTED_POSTAL_CODE + " ",
                    " " + DECRYPTED_COUNTRY + " "
            );
        }
    }
}
