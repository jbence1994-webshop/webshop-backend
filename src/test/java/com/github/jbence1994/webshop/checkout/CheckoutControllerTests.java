package com.github.jbence1994.webshop.checkout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.checkout.ApplyCouponToCheckoutSessionRequestTestObject.applyCouponToCheckoutSessionRequest;
import static com.github.jbence1994.webshop.checkout.ApplyCouponToCheckoutSessionRequestTestObject.notSanitizedApplyCouponToCheckoutSessionRequest;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionDtoTestObject.checkoutSessionDto;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionDtoTestObject.checkoutSessionDtoWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionRequestTestObject.completeCheckoutSessionRequest;
import static com.github.jbence1994.webshop.checkout.CompleteCheckoutSessionResponseTestObject.completeCheckoutSessionResponse;
import static com.github.jbence1994.webshop.checkout.CreateCheckoutSessionRequestTestObject.createCheckoutSessionRequest;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
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
    private ApplyCouponToCheckoutSessionRequestSanitizer applyCouponToCheckoutSessionRequestSanitizer;

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
    public void applyCouponToCheckoutSessionTest() {
        when(applyCouponToCheckoutSessionRequestSanitizer.sanitize(any())).thenReturn(applyCouponToCheckoutSessionRequest());
        when(checkoutService.applyCouponToCheckoutSession(any(), any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(checkoutMapper.toDto(any())).thenReturn(checkoutSessionDtoWithPercentOffTypeOfAppliedCoupon());

        var result = checkoutController.applyCouponToCheckoutSession(CART_ID, notSanitizedApplyCouponToCheckoutSessionRequest());

        assertThat(result.id(), equalTo(checkoutSessionDtoWithPercentOffTypeOfAppliedCoupon().id()));
        assertThat(result.appliedCoupon(), not(nullValue()));
        assertThat(result.appliedCoupon(), equalTo(COUPON_1_CODE));
    }

    @Test
    public void removeCouponFromCheckoutSessionTest() {
        when(checkoutService.removeCouponFromCheckoutSession(any())).thenReturn(checkoutSession());
        when(checkoutMapper.toDto(any())).thenReturn(checkoutSessionDto());

        var result = checkoutController.removeCouponFromCheckoutSession(CART_ID);

        assertThat(result.id(), equalTo(checkoutSessionDto().id()));
        assertThat(result.appliedCoupon(), is(nullValue()));
    }

    @Test
    public void completeCheckoutSessionTest() {
        when(checkoutService.completeCheckoutSession(any())).thenReturn(completeCheckoutSessionResponse());

        var result = checkoutController.completeCheckoutSession(completeCheckoutSessionRequest());

        assertThat(result, not(nullValue()));
        assertThat(result.orderId(), equalTo(1L));
    }
}
