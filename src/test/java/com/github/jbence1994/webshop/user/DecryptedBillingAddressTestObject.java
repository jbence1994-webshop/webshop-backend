package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;

public final class DecryptedBillingAddressTestObject {
    public static DecryptedBillingAddress decryptedBillingAddress() {
        return buildAddress(1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public static DecryptedBillingAddress decryptedBillingAddressAfterMappingFromDto() {
        return buildAddress(null, null, null);
    }

    private static DecryptedBillingAddress buildAddress(Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new DecryptedBillingAddress(
                userId,
                null,
                DECRYPTED_ADDRESS_LINE,
                DECRYPTED_MUNICIPALITY,
                DECRYPTED_PROVINCE,
                DECRYPTED_POSTAL_CODE,
                DECRYPTED_COUNTRY,
                createdAt,
                updatedAt
        );
    }
}
