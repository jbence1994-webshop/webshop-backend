package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.DecryptedAddressTestConstants.DECRYPTED_PROVINCE;

public final class DecryptedAddressTestObject {
    public static DecryptedAddress decryptedAddress() {
        return buildAddress(1L, LocalDateTime.now(), LocalDateTime.now());
    }

    public static DecryptedAddress decryptedAddressAfterMappingFromDto() {
        return buildAddress(null, null, null);
    }

    private static DecryptedAddress buildAddress(Long userId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new DecryptedAddress(
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
