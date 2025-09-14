package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class FixedAmountCartTotalAdjustmentStrategyTests {

    @Test
    public void adjustCartTotalTest() {
        var result = CartTotalAdjustmentStrategyFactory
                .getCartTotalAdjustmentStrategy(DiscountType.FIXED_AMOUNT)
                .adjustCartTotal(BigDecimal.valueOf(49.99), BigDecimal.valueOf(15.00));

        assertThat(result.cartTotal(), comparesEqualTo(BigDecimal.valueOf(34.99)));
        assertThat(result.discountAmount(), comparesEqualTo(BigDecimal.valueOf(15.00)));
    }
}
