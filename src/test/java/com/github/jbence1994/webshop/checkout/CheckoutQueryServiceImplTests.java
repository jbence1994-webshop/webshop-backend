package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession1;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutQueryServiceImplTests {

    @Mock
    private CheckoutRepository checkoutRepository;

    @InjectMocks
    private CheckoutQueryServiceImpl checkoutQueryService;

    @Test
    public void getCheckoutSessionTest_HappyPath() {
        when(checkoutRepository.findById(any())).thenReturn(Optional.of(checkoutSession1()));

        var result = checkoutQueryService.getCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(checkoutSession1().getId())),
                hasProperty("appliedCoupon", equalTo(checkoutSession1().getAppliedCoupon())),
                hasProperty("status", equalTo(checkoutSession1().getStatus())),
                hasProperty("createdAt", equalTo(checkoutSession1().getCreatedAt()))
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
}
