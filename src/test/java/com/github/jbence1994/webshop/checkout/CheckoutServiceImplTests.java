package com.github.jbence1994.webshop.checkout;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.cart.CartQueryService;
import com.github.jbence1994.webshop.cart.EmptyCartException;
import com.github.jbence1994.webshop.coupon.CouponService;
import com.github.jbence1994.webshop.order.OrderService;
import com.github.jbence1994.webshop.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItemsAndFixedAmountTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.checkout.CheckoutRequestTestObject.checkoutRequest;
import static com.github.jbence1994.webshop.user.UserTestObject.user;
import static com.github.jbence1994.webshop.user.UserTestObject.userWithAvatar;
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
    private CartQueryService cartQueryService;

    @Mock
    private CouponService couponService;

    @Mock
    private OrderService orderService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    private static Stream<Arguments> rewardPointsParams() {
        return Stream.of(
                Arguments.of(
                        "Available reward points is less than the order's total price as points",
                        user()
                ),
                Arguments.of(
                        "Available reward points is greater than the order's total price as points",
                        userWithAvatar()
                )
        );
    }

    @Test
    public void checkoutTest_HappyPath_WithoutAppliedCoupon() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItem());
        when(authService.getCurrentUser()).thenReturn(user());
        doNothing().when(orderService).createOrder(any());

        var result = checkoutService.checkout(checkoutRequest());

        assertThat(result, not(nullValue()));

        verify(cartQueryService, times(1)).getCart(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
    }

    @Test
    public void checkoutTest_HappyPath_WithAppliedCoupon() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItemsAndFixedAmountTypeOfAppliedCoupon());
        when(authService.getCurrentUser()).thenReturn(user());
        doNothing().when(orderService).createOrder(any());
        doNothing().when(couponService).redeemCoupon(any(), any(), any());

        var result = checkoutService.checkout(checkoutRequest());

        assertThat(result, not(nullValue()));

        verify(cartQueryService, times(1)).getCart(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("rewardPointsParams")
    public void checkoutTest_WithAppliedCouponAndRewardPointsBurn_HappyPaths(
            String testCase,
            User user
    ) {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItemsAndFixedAmountTypeOfAppliedCoupon());
        when(authService.getCurrentUser()).thenReturn(user);
        doNothing().when(orderService).createOrder(any());
        doNothing().when(couponService).redeemCoupon(any(), any(), any());

        var result = checkoutService.checkout(checkoutRequest());

        assertThat(result, not(nullValue()));

        verify(cartQueryService, times(1)).getCart(any());
        verify(authService, times(1)).getCurrentUser();
        verify(orderService, times(1)).createOrder(any());
        verify(couponService, times(1)).redeemCoupon(any(), any(), any());
    }

    @Test
    public void checkoutTest_UnhappyPath_EmptyCartException() {
        when(cartQueryService.getCart(any())).thenReturn(emptyCart());

        var result = assertThrows(
                EmptyCartException.class,
                () -> checkoutService.checkout(checkoutRequest())
        );

        assertThat(result.getMessage(), equalTo("Cart with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69 is empty."));

        verify(cartQueryService, times(1)).getCart(any());
        verify(authService, never()).getCurrentUser();
        verify(orderService, never()).createOrder(any());
        verify(couponService, never()).redeemCoupon(any(), any(), any());
    }
}
