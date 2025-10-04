package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession1;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithEmptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.completedCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.expiredCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.expiredCheckoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.LoyaltyTestConstants.POINTS_RATE;
import static com.github.jbence1994.webshop.checkout.PaymentSessionResponseTestObject.paymentSessionResponse;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.fixedAmountExpiredCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static com.github.jbence1994.webshop.user.UserTestObject.userWithAvatar;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTests {

    @Mock
    private LoyaltyPointsCalculator loyaltyPointsCalculator;

    @Mock
    private RewardPointsConverter rewardPointsConverter;

    @Mock
    private CheckoutQueryService checkoutQueryService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private PaymentGateway paymentGateway;

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
        when(checkoutRepository.save(any())).thenReturn(checkoutSession1());

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
    public void applyCouponToCheckoutSessionTest_HappyPath() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession1());
        when(couponQueryService.getCoupon(any())).thenReturn(percentOffNotExpiredCoupon());
        when(couponQueryService.isCouponRedeemed(any())).thenReturn(false);
        when(checkoutRepository.save(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());

        assertDoesNotThrow(() -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(couponQueryService, times(1)).isCouponRedeemed(any());
        verify(checkoutRepository, times(1)).save(any());
    }

    @Test
    public void applyCouponToCheckoutSessionTest_UnhappyPath_ExpiredCheckoutSessionException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(expiredCheckoutSession());

        var result = assertThrows(
                ExpiredCheckoutSessionException.class,
                () -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, never()).getCoupon(any());
        verify(couponQueryService, never()).isCouponRedeemed(any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void applyCouponToCheckoutSessionTest_UnhappyPath_ExpiredCouponException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession1());
        when(couponQueryService.getCoupon(any())).thenReturn(fixedAmountExpiredCoupon());

        var result = assertThrows(
                ExpiredCouponException.class,
                () -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Coupon with the given code: 'WELCOME10' has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(couponQueryService, never()).isCouponRedeemed(any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void applyCouponToCheckoutSessionTest_UnhappyPath_CouponAlreadyRedeemedException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession1());
        when(couponQueryService.getCoupon(any())).thenReturn(percentOffNotExpiredCoupon());
        when(couponQueryService.isCouponRedeemed(any())).thenReturn(true);

        var result = assertThrows(
                CouponAlreadyRedeemedException.class,
                () -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Coupon with the given code: 'WELCOME10' is already redeemed. Try another one."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(couponQueryService, times(1)).isCouponRedeemed(any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void removeCouponFromCheckoutSessionTest_HappyPath() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(checkoutRepository.save(any())).thenReturn(checkoutSession1());

        assertDoesNotThrow(() -> checkoutService.removeCouponFromCheckoutSession(CART_ID));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(checkoutRepository, times(1)).save(any());
    }

    @Test
    public void removeCouponFromCheckoutSessionTest_UnhappyPath_ExpiredCheckoutSessionException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(expiredCheckoutSessionWithPercentOffTypeOfAppliedCoupon());

        var result = assertThrows(
                ExpiredCheckoutSessionException.class,
                () -> checkoutService.removeCouponFromCheckoutSession(CART_ID)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void completeCheckoutSessionTest_HappyPath_WithAppliedCoupon_RewardPointsActionEarn() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(authService.getCurrentUser()).thenReturn(user());
        when(rewardPointsConverter.toRewardPoints(any(), any())).thenReturn(74);
        doNothing().when(orderService).createOrder(any());
        doNothing().when(couponService).redeemCoupon(any(), any(), any());
        when(loyaltyPointsCalculator.calculateLoyaltyPoints(any())).thenReturn(POINTS_RATE);
        when(paymentGateway.createPaymentSession(any())).thenReturn(paymentSessionResponse());

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.EARN);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(rewardPointsConverter, times(1)).toRewardPoints(any(), any());
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, times(1)).calculateLoyaltyPoints(any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_HappyPath_WithoutAppliedCoupon_RewardPointsActionBurn() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession1());
        when(authService.getCurrentUser()).thenReturn(userWithAvatar());
        doNothing().when(orderService).createOrder(any());
        when(loyaltyPointsCalculator.calculateLoyaltyPoints(any())).thenReturn(POINTS_RATE);
        when(paymentGateway.createPaymentSession(any())).thenReturn(paymentSessionResponse());

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.BURN);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(rewardPointsConverter, never()).toRewardPoints(any(), any());
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, times(1)).calculateLoyaltyPoints(any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_ExpiredCheckoutSessionException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(expiredCheckoutSession());

        var result = assertThrows(
                ExpiredCheckoutSessionException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.EARN)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(rewardPointsConverter, never()).toRewardPoints(any(), any());
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, never()).calculateLoyaltyPoints(any());
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_CheckoutSessionAlreadyCompletedException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(completedCheckoutSession());

        var result = assertThrows(
                CheckoutSessionAlreadyCompletedException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.EARN)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 already completed."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(rewardPointsConverter, never()).toRewardPoints(any(), any());
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, never()).calculateLoyaltyPoints(any());
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_EmptyCartException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithEmptyCart());

        var result = assertThrows(
                EmptyCartException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.EARN)
        );

        assertThat(result.getMessage(), equalTo("Cart with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 is empty."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(rewardPointsConverter, never()).toRewardPoints(any(), any());
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, never()).calculateLoyaltyPoints(any());
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_PaymentException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession1());
        when(authService.getCurrentUser()).thenReturn(user());
        when(rewardPointsConverter.toRewardPoints(any(), any())).thenReturn(74);
        doNothing().when(orderService).createOrder(any());
        when(loyaltyPointsCalculator.calculateLoyaltyPoints(any())).thenReturn(POINTS_RATE);
        doThrow(new PaymentException("Payment exception.")).when(paymentGateway).createPaymentSession(any());

        var result = assertThrows(
                PaymentException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID, RewardPointsAction.EARN)
        );

        assertThat(result.getMessage(), equalTo("Payment exception."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(rewardPointsConverter, times(1)).toRewardPoints(any(), any());
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(loyaltyPointsCalculator, times(1)).calculateLoyaltyPoints(any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, times(1)).deleteOrder(any());
    }
}
