package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.checkout.CheckoutSessionDtoTestObject.checkoutSessionDto;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionRequestTestObject.completeCheckoutSessionRequest;
import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionResponseTestObject.completeCheckoutSessionResponse;
import static com.github.jbence1994.webshop.checkout.CreateCheckoutSessionRequestTestObject.createCheckoutSessionRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutControllerTests {

    @Mock
    private CheckoutService checkoutService;

    @Mock
    private CheckoutMapper checkoutMapper;

    @InjectMocks
    private CheckoutController checkoutController;

    @Test
    public void createCheckoutSessionTest() {
        when(checkoutService.createCheckoutSession(any())).thenReturn(checkoutSession());
        when(checkoutMapper.toDto(any())).thenReturn(checkoutSessionDto());

        var result = checkoutController.createCheckoutSession(createCheckoutSessionRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().id(), equalTo(checkoutSessionDto().id()));
        assertThat(result.getBody().cartId(), equalTo(checkoutSessionDto().cartId()));
        assertThat(result.getBody().appliedCoupon(), is(nullValue()));
        assertThat(result.getBody().status(), equalTo(checkoutSessionDto().status()));
        assertThat(result.getBody().createdAt(), equalTo(checkoutSessionDto().createdAt()));
    }

    @Test
    public void completeCheckoutSessionTest() {
        when(checkoutService.completeCheckoutSession(any())).thenReturn(completeCheckoutSessionResponse());

        var result = checkoutController.completeCheckoutSession(completeCheckoutSessionRequest());

        assertThat(result, not(nullValue()));
        assertThat(result.orderId(), equalTo(1L));
    }
}
