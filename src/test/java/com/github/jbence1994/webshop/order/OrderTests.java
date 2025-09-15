package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession1;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

public class OrderTests {

    @Test
    public void fromTests() {
        var result = Order.from(user(), checkoutSession1());

        assertThat(result, allOf(
                hasProperty("totalPrice", comparesEqualTo(checkoutSession1().getCartTotal())),
                hasProperty("discountAmount", comparesEqualTo(checkoutSession1().getDiscountAmount())),
                hasProperty("status", equalTo(OrderStatus.CREATED))
        ));
        assertThat(result.getItems().size(), equalTo(1));
    }
}
