package com.github.jbence1994.webshop.user;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;

public final class BillingAddressDtoTestObject {
    public static BillingAddressDto billingAddressDto() {
        return new BillingAddressDto(
                DECRYPTED_ADDRESS_LINE,
                DECRYPTED_MUNICIPALITY,
                DECRYPTED_PROVINCE,
                DECRYPTED_POSTAL_CODE,
                DECRYPTED_COUNTRY
        );
    }
}
