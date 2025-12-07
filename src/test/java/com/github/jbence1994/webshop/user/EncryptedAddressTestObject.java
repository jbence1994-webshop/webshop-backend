package com.github.jbence1994.webshop.user;

import java.time.LocalDateTime;

import static com.github.jbence1994.webshop.user.EncryptedAddressTestConstants.ENCRYPTED_ADDRESS_LINE;
import static com.github.jbence1994.webshop.user.EncryptedAddressTestConstants.ENCRYPTED_COUNTRY;
import static com.github.jbence1994.webshop.user.EncryptedAddressTestConstants.ENCRYPTED_MUNICIPALITY;
import static com.github.jbence1994.webshop.user.EncryptedAddressTestConstants.ENCRYPTED_POSTAL_CODE;
import static com.github.jbence1994.webshop.user.EncryptedAddressTestConstants.ENCRYPTED_PROVINCE;

public final class EncryptedAddressTestObject {
    public static EncryptedAddress encryptedAddress() {
        return buildAddress(LocalDateTime.now(), LocalDateTime.now());
    }

    private static EncryptedAddress buildAddress(LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new EncryptedAddress(
                1L,
                null,
                ENCRYPTED_ADDRESS_LINE,
                ENCRYPTED_MUNICIPALITY,
                ENCRYPTED_PROVINCE,
                ENCRYPTED_POSTAL_CODE,
                ENCRYPTED_COUNTRY,
                createdAt,
                updatedAt
        );
    }
}
