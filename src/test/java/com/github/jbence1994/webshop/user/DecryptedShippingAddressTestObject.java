package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;

public final class DecryptedShippingAddressTestObject {
    public static DecryptedShippingAddress decryptedShippingAddress() {
        return buildAddress(1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public static DecryptedShippingAddress decryptedShippingAddressAfterMappingFromDto() {
        return buildAddress(null, null, null);
    }

    private static DecryptedShippingAddress buildAddress(Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new DecryptedShippingAddress(
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
