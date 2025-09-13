package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.loyalty.LoyaltyPointsCalculator;
import com.github.jbence1994.webshop.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithEmptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.completedCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.loyalty.LoyaltyTestConstants.POINTS_RATE;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTests {

    @Mock
    private LoyaltyPointsCalculator loyaltyPointsCalculator;

    @Mock
    private CheckoutQueryService checkoutQueryService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private CouponService couponService;

    @Mock
    private OrderService orderService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Test
    public void createCheckoutSession_HappyPath() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItem());
        when(checkoutRepository.save(any())).thenReturn(checkoutSession());

        var result = checkoutService.createCheckoutSession(CART_ID);

        assertThat(result, not(nullValue()));

        verify(cartQueryService, times(1)).getCart(any());
        verify(checkoutRepository, times(1)).save(any());
    }

    @Test
    public void createCheckoutSession_UnhappyPath_EmptyCartException() {
        when(cartQueryService.getCart(any())).thenReturn(emptyCart());

        var result = assertThrows(
                EmptyCartException.class,
                () -> checkoutService.createCheckoutSession(CART_ID)
        );

        assertThat(result.getMessage(), equalTo("Cart with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 is empty."));

        verify(cartQueryService, times(1)).getCart(any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void completeCheckoutSessionTest_HappyPath_WithoutAppliedCoupon() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(authService.getCurrentUser()).thenReturn(user());
        doNothing().when(orderService).createOrder(any());
        when(loyaltyPointsCalculator.calculateLoyaltyPoints(any())).thenReturn(POINTS_RATE);

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(loyaltyPointsCalculator, times(1)).calculateLoyaltyPoints(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
    }

    @Test
    public void completeCheckoutSessionTest_HappyPath_WithAppliedCoupon() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(authService.getCurrentUser()).thenReturn(user());
        doNothing().when(orderService).createOrder(any());
        doNothing().when(couponService).redeemCoupon(any(), any(), any());
        when(loyaltyPointsCalculator.calculateLoyaltyPoints(any())).thenReturn(POINTS_RATE);

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, times(1)).calculateLoyaltyPoints(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_CheckoutSessionAlreadyCompletedException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(completedCheckoutSession());

        var result = assertThrows(
                CheckoutSessionAlreadyCompletedException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 already completed."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, never()).calculateLoyaltyPoints(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_EmptyCartException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithEmptyCart());

        var result = assertThrows(
                EmptyCartException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo("Cart with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 is empty."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, never()).calculateLoyaltyPoints(any());
    }
}
