package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class PercentOffPriceAdjustmentStrategyTests {

    @Test
    public void adjustPriceTest() {
        var result = PriceAdjustmentStrategyFactory
                .getPriceAdjustmentStrategy(DiscountType.PERCENT_OFF)
                .adjustPrice(
                        BigDecimal.valueOf(49.99),
                        BigDecimal.valueOf(0.10)
                );

        assertThat(result.getTotalPrice(), comparesEqualTo(BigDecimal.valueOf(44.99)));
        assertThat(result.getDiscountAmount(), comparesEqualTo(BigDecimal.valueOf(5.00)));
        assertThat(result.getShippingCost(), comparesEqualTo(BigDecimal.valueOf(20.00)));
    }
}
