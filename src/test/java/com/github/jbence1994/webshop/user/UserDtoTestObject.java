package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.BillingAddressDtoTestObject.billingAddressDto;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_FIRST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_LAST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.ShippingAddressDtoTestObject.shippingAddressDto;

public final class UserDtoTestObject {
    public static UserDto userDto() {
        return new UserDto(
                1L,
                DECRYPTED_EMAIL_1,
                DECRYPTED_FIRST_NAME,
                DECRYPTED_MIDDLE_NAME,
                DECRYPTED_LAST_NAME,
                DECRYPTED_DATE_OF_BIRTH,
                DECRYPTED_PHONE_NUMBER,
                billingAddressDto(),
                shippingAddressDto()
        );
    }
}
