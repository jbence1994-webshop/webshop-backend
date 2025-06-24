package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class OrderTests {

    @Test
    public void fromCartTest() {
        var result = Order.fromCart(cartWithTwoItems(), user());

        assertThat(result, not(nullValue()));
        assertThat(result.getItems().size(), equalTo(2));
        assertThat(result, allOf(
                hasProperty("totalPrice", equalTo(BigDecimal.valueOf(139.98))),
                hasProperty("status", equalTo(OrderStatus.PENDING))
        ));
    }
}
