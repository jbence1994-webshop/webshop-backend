package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class PercentOffCartTotalAdjustmentStrategyTests {

    @Test
    public void adjustCartTotalTest() {
        var result = CartTotalAdjustmentStrategyFactory
                .getStrategy(DiscountType.PERCENT_OFF)
                .adjustCartTotal(BigDecimal.valueOf(49.99), BigDecimal.valueOf(0.10));

        assertThat(result.getCartTotal(), comparesEqualTo(BigDecimal.valueOf(44.99)));
        assertThat(result.getDiscountAmount(), comparesEqualTo(BigDecimal.valueOf(5.00)));
    }
}
