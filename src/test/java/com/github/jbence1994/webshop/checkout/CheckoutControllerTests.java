package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionRequestTestObject.completeCheckoutSessionRequest;
import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionResponseTestObject.completeCheckoutSessionResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutControllerTests {

    @Mock
    private CheckoutService checkoutService;

    @InjectMocks
    private CheckoutController checkoutController;

    @Test
    public void completeCheckoutSessionTest() {
        when(checkoutService.completeCheckoutSession(any())).thenReturn(completeCheckoutSessionResponse());

        var result = checkoutController.completeCheckoutSession(completeCheckoutSessionRequest());

        assertThat(result, not(nullValue()));
        assertThat(result.orderId(), equalTo(1L));
    }
}
