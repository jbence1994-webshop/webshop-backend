package com.github.jbence1994.webshop.user;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static com.github.jbence1994.webshop.user.ProfileTestObject.profileWithoutAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProfileTests {
    private final Profile profile = profileWithoutAvatar();

    @Test
    public void removeFavoriteProductTest() {
        profile.addFavoriteProduct(product1());
        profile.addFavoriteProduct(product2());

        profile.removeFavoriteProduct(1L);

        assertThat(profile.getFavoriteProducts().size(), equalTo(1));
    }
}
