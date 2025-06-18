package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.github.jbence1994.webshop.cart.CartDtoTestObject.cartDto;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class CartDtoEnricherTests {

    @Mock
    private PriceAdjustmentStrategyFactory priceAdjustmentStrategyFactory;

    @InjectMocks
    private CartDtoEnricher cartDtoEnricher;

    @Test
    public void enrichTest() {
        var result = cartDtoEnricher.enrich(cartDto(), cartWithOneItem());

        assertThat(cartDto().getTotalPrice(), is(nullValue()));
        assertThat(result.getTotalPrice(), not(nullValue()));
        assertThat(result.getTotalPrice(), equalTo(BigDecimal.valueOf(89.99)));
        assertThat(result.getTotalPrice(), equalTo(cartWithOneItem().calculateTotalPrice(priceAdjustmentStrategyFactory)));
    }
}
