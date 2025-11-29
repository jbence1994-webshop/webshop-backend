package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class FixedAmountCartTotalAdjustmentStrategyTests {

    @Test
    public void adjustCartTotalTest() {
        var result = CartTotalAdjustmentStrategyFactory
                .getStrategy(DiscountType.FIXED_AMOUNT)
                .adjustCartTotal(BigDecimal.valueOf(49.99), BigDecimal.valueOf(15.00));

        assertThat(result.getCartTotal(), comparesEqualTo(BigDecimal.valueOf(34.99)));
        assertThat(result.getDiscountAmount(), comparesEqualTo(BigDecimal.valueOf(15.00)));
    }
}
