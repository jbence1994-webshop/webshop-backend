package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.cart.CartNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class CheckoutControllerExceptionHandlerTests {

    @InjectMocks
    private CheckoutControllerExceptionHandler checkoutControllerExceptionHandler;

    @Test
    public void handleCheckoutSessionNotFoundExceptionTest() {
        var result = checkoutControllerExceptionHandler.handleCheckoutSessionNotFoundException(new CheckoutSessionNotFoundException(CHECKOUT_SESSION_ID));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No checkout session was found with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2."));
    }

    @Test
    public void handleCheckoutSessionAlreadyCompletedExceptionTest() {
        var result = checkoutControllerExceptionHandler.handleCheckoutSessionAlreadyCompletedException(new CheckoutSessionAlreadyCompletedException(CHECKOUT_SESSION_ID));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CONFLICT));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 already completed."));
    }

    @Test
    public void handleCartNotFoundExceptionTest() {
        var result = checkoutControllerExceptionHandler.handleCartNotFoundException(new CartNotFoundException(CART_ID));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No cart was found with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69."));
    }
}
