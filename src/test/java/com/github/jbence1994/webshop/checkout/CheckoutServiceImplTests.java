package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponAlreadyRedeemedException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.coupon.ExpiredCouponException;
import com.github.jbence1994.webshop.order.OrderQueryService;
import com.github.jbence1994.webshop.order.OrderService;
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
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.canceledCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithEmptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.checkoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.completedCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.expiredCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.expiredCheckoutSessionWithPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.checkout.CheckoutSessionTestObject.failedCheckoutSession;
import static com.github.jbence1994.webshop.checkout.CheckoutTestConstants.CHECKOUT_SESSION_ID;
import static com.github.jbence1994.webshop.checkout.PaymentResultTestObject.paymentIntentSucceeded;
import static com.github.jbence1994.webshop.checkout.PaymentSessionResponseTestObject.paymentSessionResponse;
import static com.github.jbence1994.webshop.checkout.WebhookRequestTestObject.webhookRequest;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.fixedAmountExpiredCoupon;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.percentOffNotExpiredCoupon;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithAvatar;
import static com.github.jbence1994.webshop.user.EncryptedUserTestObject.encryptedUser1WithoutAvatar;
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
    private CheckoutQueryService checkoutQueryService;

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private OrderQueryService orderQueryService;

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

    private static Stream<Arguments> invalidCheckoutSessionStateParams() {
        return Stream.of(
                Arguments.of(Named.of("CANCELED", canceledCheckoutSession()), CheckoutStatus.CANCELED),
                Arguments.of(Named.of("COMPLETED", completedCheckoutSession()), CheckoutStatus.COMPLETED),
                Arguments.of(Named.of("FAILED", failedCheckoutSession()), CheckoutStatus.FAILED)
        );
    }

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
    public void applyCouponToCheckoutSessionTest_HappyPath() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(couponQueryService.getCoupon(any())).thenReturn(percentOffNotExpiredCoupon());
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(couponQueryService.hasUserRedeemedCoupon(any(), any())).thenReturn(false);
        when(checkoutRepository.save(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());

        assertDoesNotThrow(() -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(authService, times(1)).getCurrentUser();
        verify(couponQueryService, times(1)).hasUserRedeemedCoupon(any(), any());
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
        verify(couponQueryService, never()).hasUserRedeemedCoupon(any(), any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void applyCouponToCheckoutSessionTest_UnhappyPath_ExpiredCouponException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(couponQueryService.getCoupon(any())).thenReturn(fixedAmountExpiredCoupon());

        var result = assertThrows(
                ExpiredCouponException.class,
                () -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Coupon with the given code: 'WELCOME10' has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(couponQueryService, never()).hasUserRedeemedCoupon(any(), any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void applyCouponToCheckoutSessionTest_UnhappyPath_CouponAlreadyRedeemedException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(couponQueryService.getCoupon(any())).thenReturn(percentOffNotExpiredCoupon());
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        when(couponQueryService.hasUserRedeemedCoupon(any(), any())).thenReturn(true);

        var result = assertThrows(
                CouponAlreadyRedeemedException.class,
                () -> checkoutService.applyCouponToCheckoutSession(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Coupon with the given code: 'WELCOME10' is already redeemed."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(authService, times(1)).getCurrentUser();
        verify(couponQueryService, times(1)).hasUserRedeemedCoupon(any(), any());
        verify(checkoutRepository, never()).save(any());
    }

    @Test
    public void removeCouponFromCheckoutSessionTest_HappyPath() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(checkoutRepository.save(any())).thenReturn(checkoutSession());

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
    public void completeCheckoutSessionTest_HappyPath_WithAppliedCoupon() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSessionWithPercentOffTypeOfAppliedCoupon());
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        doNothing().when(orderService).createOrder(any());
        doNothing().when(couponService).redeemCoupon(any(), any(), any());
        when(paymentGateway.createPaymentSession(any())).thenReturn(paymentSessionResponse());

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_HappyPath_WithoutAppliedCoupon() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithAvatar());
        doNothing().when(orderService).createOrder(any());
        when(paymentGateway.createPaymentSession(any())).thenReturn(paymentSessionResponse());

        var result = checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID);

        assertThat(result, not(nullValue()));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_ExpiredCheckoutSessionException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(expiredCheckoutSession());

        var result = assertThrows(
                ExpiredCheckoutSessionException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 has expired."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidCheckoutSessionStateParams")
    public void completeCheckoutSessionTest_UnhappyPaths_InvalidCheckoutSessionStateException(
            CheckoutSession checkoutSession,
            CheckoutStatus status
    ) {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession);

        var result = assertThrows(
                InvalidCheckoutSessionStateException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo(String.format("Checkout session with the given ID: 401c3a9e-c1ae-4a39-956b-9af3ed28a4e2 is in the state of: %s.", status)));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, never()).getCurrentUser();
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
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
        verify(paymentGateway, never()).createPaymentSession(any());
        verify(orderService, never()).deleteOrder(any());
    }

    @Test
    public void completeCheckoutSessionTest_UnhappyPath_PaymentException() {
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(authService.getCurrentUser()).thenReturn(encryptedUser1WithoutAvatar());
        doNothing().when(orderService).createOrder(any());
        doThrow(new PaymentException("Payment exception.")).when(paymentGateway).createPaymentSession(any());

        var result = assertThrows(
                PaymentException.class,
                () -> checkoutService.completeCheckoutSession(CHECKOUT_SESSION_ID)
        );

        assertThat(result.getMessage(), equalTo("Payment exception."));

        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
        verify(paymentGateway, times(1)).createPaymentSession(any());
        verify(orderService, times(1)).deleteOrder(any());
    }

    @Test
    public void handleCompleteCheckoutSessionWebhookEventTest_HappyPath_PaymentIntentSucceeded() {
        when(paymentGateway.parseWebhookRequest(any())).thenReturn(Optional.of(paymentIntentSucceeded()));
        when(orderQueryService.getOrder(any())).thenReturn(createdOrder1());
        doNothing().when(orderService).updateOrder(any());
        when(checkoutQueryService.getCheckoutSession(any())).thenReturn(checkoutSession());
        when(checkoutRepository.save(any())).thenReturn(completedCheckoutSession());

        assertDoesNotThrow(() -> checkoutService.handleCompleteCheckoutSessionWebhookEvent(webhookRequest()));

        verify(paymentGateway, times(1)).parseWebhookRequest(any());
        verify(orderQueryService, times(1)).getOrder(any());
        verify(orderService, times(1)).updateOrder(any());
        verify(checkoutQueryService, times(1)).getCheckoutSession(any());
        verify(checkoutRepository, times(1)).save(any());
    }

    @Test
    public void handleCompleteCheckoutSessionWebhookEventTest_UnhappyPath() {
        when(paymentGateway.parseWebhookRequest(any())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> checkoutService.handleCompleteCheckoutSessionWebhookEvent(webhookRequest()));

        verify(paymentGateway, times(1)).parseWebhookRequest(any());
        verify(paymentGateway, times(1)).parseWebhookRequest(any());
        verify(orderQueryService, never()).getOrder(any());
        verify(orderService, never()).updateOrder(any());
        verify(checkoutQueryService, never()).getCheckoutSession(any());
        verify(checkoutRepository, never()).save(any());
    }
}
