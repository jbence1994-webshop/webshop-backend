package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.user.DecryptedBillingAddressTestObject.decryptedBillingAddress;
import static com.github.jbence1994.webshop.user.DecryptedShippingAddressTestObject.decryptedShippingAddress;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_FIRST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_LAST_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.DECRYPTED_PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.DecryptedUserTestConstants.RAW_PASSWORD;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.HASHED_PASSWORD;

public final class DecryptedUserTestObject {
    public static DecryptedUser decryptedUser1WithoutAvatar() {
        return buildUser(1L, HASHED_PASSWORD, null, new ArrayList<>());
    }

    public static DecryptedUser decryptedUser1WithAvatar() {
        return buildUser(1L, HASHED_PASSWORD, AVATAR_FILE_NAME, new ArrayList<>());
    }

    public static DecryptedUser decryptedUser1AfterMappingFromDto() {
        return buildUser(null, RAW_PASSWORD, null, new ArrayList<>());
    }

    private static DecryptedUser buildUser(
            Long id,
            String password,
            String avatarFileName,
            List<Product> favoriteProducts
    ) {
        return new DecryptedUser(
                id,
                DECRYPTED_EMAIL_1,
                password,
                DECRYPTED_FIRST_NAME,
                DECRYPTED_MIDDLE_NAME,
                DECRYPTED_LAST_NAME,
                DECRYPTED_DATE_OF_BIRTH,
                DECRYPTED_PHONE_NUMBER,
                avatarFileName,
                0,
                Role.ADMIN,
                LocalDateTime.now(),
                LocalDateTime.now(),
                decryptedBillingAddress(),
                decryptedShippingAddress(),
                favoriteProducts
        );
    }
}
