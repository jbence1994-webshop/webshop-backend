package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.image.ImageTestConstants.ENCRYPTED_AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.EncryptedBillingAddressTestObject.encryptedBillingAddress;
import static com.github.jbence1994.webshop.user.EncryptedShippingAddressTestObject.encryptedShippingAddress;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_EMAIL_1;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_EMAIL_2;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_FIRST_NAME;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_LAST_NAME;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.ENCRYPTED_PHONE_NUMBER;
import static com.github.jbence1994.webshop.user.EncryptedUserTestConstants.HASHED_PASSWORD;

public final class EncryptedUserTestObject {
    public static EncryptedUser encryptedUser1WithoutAvatar() {
        return buildUser(1L, ENCRYPTED_EMAIL_1, null, Role.ADMIN, new ArrayList<>());
    }

    public static EncryptedUser encryptedUser1WithAvatar() {
        return buildUser(1L, ENCRYPTED_EMAIL_1, ENCRYPTED_AVATAR_FILE_NAME, Role.ADMIN, new ArrayList<>());
    }

    public static EncryptedUser encryptedUser1WithFavoriteProducts() {
        return buildUser(1L, ENCRYPTED_EMAIL_1, ENCRYPTED_AVATAR_FILE_NAME, Role.ADMIN, List.of(product1()));
    }

    public static EncryptedUser encryptedUser2WithoutAvatar() {
        return buildUser(2L, ENCRYPTED_EMAIL_2, null, Role.USER, new ArrayList<>());
    }

    private static EncryptedUser buildUser(
            Long id,
            String email,
            String avatarFileName,
            Role role,
            List<Product> favoriteProducts
    ) {
        return new EncryptedUser(
                id,
                email,
                HASHED_PASSWORD,
                ENCRYPTED_FIRST_NAME,
                ENCRYPTED_MIDDLE_NAME,
                ENCRYPTED_LAST_NAME,
                ENCRYPTED_DATE_OF_BIRTH,
                ENCRYPTED_PHONE_NUMBER,
                avatarFileName,
                0,
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                encryptedBillingAddress(),
                encryptedShippingAddress(),
                favoriteProducts
        );
    }
}
