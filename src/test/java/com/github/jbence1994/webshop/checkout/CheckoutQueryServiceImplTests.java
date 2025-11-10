package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutQueryServiceImplTests {

    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private CheckoutQueryServiceImpl checkoutQueryService;

    private static Stream<Arguments> existsByCartIdTestParams() {
        return Stream.of(
                Arguments.of(Named.of("Exists by cart ID", true)),
                Arguments.of(Named.of("Not exists by cart ID", false))
        );
    }

    @Test
    public void getCheckoutSessionTest_HappyPath() {
        when(checkoutRepository.findById(any())).thenReturn(Optional.of(checkoutSession()));

        var result = checkoutQueryService.getCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(checkoutSession().getId())),
                hasProperty("appliedCoupon", equalTo(checkoutSession().getAppliedCoupon())),
                hasProperty("status", equalTo(checkoutSession().getStatus())),
                hasProperty("createdAt", equalTo(checkoutSession().getCreatedAt()))
        ));
    }

    @Test
    public void getCheckoutSessionTest_UnhappyPath_CheckoutSessionNotFoundException() {
        when(checkoutRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                CheckoutSessionNotFoundException.class,
                () -> checkoutQueryService.getCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo("No checkout session was found with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2."));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("existsByCartIdTestParams")
    public void existByCartIdTests(boolean expectedResult) {
        when(checkoutRepository.existsByCartId(any())).thenReturn(expectedResult);

        var result = checkoutQueryService.existsByCartId(CART_ID);

        assertThat(result, is(expectedResult));

        verify(checkoutRepository, times(1)).existsByCartId(any());
    }
}
