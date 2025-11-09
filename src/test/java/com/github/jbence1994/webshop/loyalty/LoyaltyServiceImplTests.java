package com.github.jbence1994.webshop.loyalty;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.loyalty.LoyaltyTestConstants.LOYALTY_CONVERSION_RATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoyaltyServiceImplTests {

    @Mock
    private LoyaltyConversionConfig loyaltyConversionConfig;

    @InjectMocks
    private LoyaltyServiceImpl loyaltyService;

    private static Stream<Arguments> calculateLoyaltyPointsAmountTestParams() {
        return Stream.of(
                Arguments.of(Named.of("Order total: $5.99, expected loyalty points: 9", BigDecimal.valueOf(5.99)), 1),
                Arguments.of(Named.of("Order total: $10.99, expected loyalty points: 9", BigDecimal.valueOf(10.99)), 2),
                Arguments.of(Named.of("Order total: $15.99, expected loyalty points: 9", BigDecimal.valueOf(15.99)), 3),
                Arguments.of(Named.of("Order total: $20.99, expected loyalty points: 9", BigDecimal.valueOf(20.99)), 4),
                Arguments.of(Named.of("Order total: $25.99, expected loyalty points: 9", BigDecimal.valueOf(25.99)), 5)
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("calculateLoyaltyPointsAmountTestParams")
    public void calculateLoyaltyPointsAmountTests(BigDecimal orderTotal, int expectedLoyaltyPoints) {
        when(loyaltyConversionConfig.rate()).thenReturn(LOYALTY_CONVERSION_RATE);

        var result = loyaltyService.calculateLoyaltyPointsAmount(orderTotal);

        assertThat(result, equalTo(expectedLoyaltyPoints));

        verify(loyaltyConversionConfig, times(1)).rate();
    }
}
