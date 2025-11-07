package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.user.MembershipTier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jbence1994.webshop.cart.CartItemTestObject.cartItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BronzeCalculationStrategyTests {

    @Test
    public void calculateLoyaltyPointsTest() {
        var result = LoyaltyCalculationStrategyFactory
                .getLoyaltyCalculationStrategy(MembershipTier.BRONZE)
                .calculateLoyaltyPoints(List.of(cartItem()));

        assertThat(result, equalTo(2));
    }
}
