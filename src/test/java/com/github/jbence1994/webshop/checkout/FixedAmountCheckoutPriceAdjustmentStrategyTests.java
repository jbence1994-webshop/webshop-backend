package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.coupon.DiscountType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.SHIPPING_COST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

public class FixedAmountCheckoutPriceAdjustmentStrategyTests {

    @Test
    public void adjustCheckoutPriceTest() {
        var result = CheckoutPriceAdjustmentStrategyFactory
                .getCheckoutPriceAdjustmentStrategy(DiscountType.FIXED_AMOUNT)
                .adjustCheckoutPrice(
                        BigDecimal.valueOf(49.99),
                        BigDecimal.valueOf(15.00),
                        SHIPPING_COST
                );

        assertThat(result.getTotalPrice(), comparesEqualTo(BigDecimal.valueOf(34.99)));
        assertThat(result.getDiscountAmount(), comparesEqualTo(BigDecimal.valueOf(15.00)));
        assertThat(result.getShippingCost(), comparesEqualTo(BigDecimal.valueOf(7.99)));
    }
}
