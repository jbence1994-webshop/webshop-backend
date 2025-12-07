package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EncryptedUserTests {
    private final EncryptedUser user = encryptedUser1WithoutAvatar();

    @Test
    public void removeFavoriteProductTest() {
        user.addFavoriteProduct(product1());
        user.addFavoriteProduct(product2());

        user.removeFavoriteProduct(1L);

        assertThat(user.getFavoriteProducts().size(), equalTo(1));
    }
}
