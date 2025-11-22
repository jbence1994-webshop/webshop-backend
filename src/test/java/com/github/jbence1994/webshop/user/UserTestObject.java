package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static com.github.jbence1994.webshop.image.ImageTestConstants.AVATAR_FILE_NAME;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.user.AddressTestObject.address;
import static com.github.jbence1994.webshop.user.UserTestConstants.DATE_OF_BIRTH;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_1;
import static com.github.jbence1994.webshop.user.UserTestConstants.EMAIL_2;
import static com.github.jbence1994.webshop.user.UserTestConstants.FIRST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.HASHED_PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.LAST_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.MIDDLE_NAME;
import static com.github.jbence1994.webshop.user.UserTestConstants.PASSWORD;
import static com.github.jbence1994.webshop.user.UserTestConstants.PHONE_NUMBER;

public final class UserTestObject {
    public static User user1WithoutAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, null, Role.ADMIN, new ArrayList<>());
    }

    public static User user1WithAvatar() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, AVATAR_FILE_NAME, Role.ADMIN, new ArrayList<>());
    }

    public static User user1AfterMappingFromDto() {
        return buildUser(null, EMAIL_1, PASSWORD, null, Role.ADMIN, new ArrayList<>());
    }

    public static User user1WithFavoriteProducts() {
        return buildUser(1L, EMAIL_1, HASHED_PASSWORD, AVATAR_FILE_NAME, Role.ADMIN, List.of(product1()));
    }

    public static User user2WithoutAvatar() {
        return buildUser(2L, EMAIL_2, HASHED_PASSWORD, null, Role.USER, new ArrayList<>());
    }

    private static User buildUser(
            Long id,
            String email,
            String password,
            String avatarFileName,
            Role role,
            List<Product> favoriteProducts
    ) {
        return new User(
                id,
                email,
                password,
                FIRST_NAME,
                MIDDLE_NAME,
                LAST_NAME,
                DATE_OF_BIRTH,
                PHONE_NUMBER,
                avatarFileName,
                role,
                LocalDateTime.now(),
                LocalDateTime.now(),
                address(),
                List.of(percentOffNotExpiredCoupon()),
                favoriteProducts
        );
    }
}
