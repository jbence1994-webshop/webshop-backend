package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartItemTestObject.cartItem1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class CartItemTests {

    @Test
    public void getTotalPriceTest() {
        var result = cartItem1().getTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(49.99)));
    }
}
