package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.coupon.CouponExpiredException;
import com.github.jbence1994.webshop.coupon.CouponQueryService;
import com.github.jbence1994.webshop.product.ProductQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.CartItemTestObject.cartItem;
import static com.github.jbence1994.webshop.cart.CartItemTestObject.updatedCartItem;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItemAndAppliedCoupon;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.cart.CartTestObject.updatedCart;
import static com.github.jbence1994.webshop.coupon.CouponTestConstants.COUPON_1_CODE;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon1;
import static com.github.jbence1994.webshop.coupon.CouponTestObject.coupon3;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTests {

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private CouponQueryService couponQueryService;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void createCartTest() {
        when(cartRepository.save(any())).thenReturn(emptyCart());

        var result = cartService.createCart();

        assertThat(result.getItems(), is(empty()));
    }

    @Test
    public void addProductToCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(emptyCart());
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(cartRepository.save(any())).thenReturn(cartWithOneItem());

        var result = cartService.addProductToCart(CART_ID, 1L);

        assertThat(result.getQuantity(), equalTo(cartItem().getQuantity()));
        assertThat(result.calculateTotalPrice(), equalTo(cartItem().calculateTotalPrice()));
    }

    @Test
    public void updateCartItemTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItems());
        when(cartQueryService.getCartItem(any(), any())).thenReturn(cartItem());
        when(cartRepository.save(any())).thenReturn(updatedCart());

        var result = cartService.updateCartItem(CART_ID, 1L, 2);

        assertThat(result.getQuantity(), equalTo(updatedCartItem().getQuantity()));
        assertThat(result.calculateTotalPrice(), equalTo(updatedCartItem().calculateTotalPrice()));
    }

    @Test
    public void deleteCartItemTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItems());
        when(cartRepository.save(any())).thenReturn(cartWithOneItem());

        assertDoesNotThrow(() -> cartService.deleteCartItem(CART_ID, 1L));
    }

    @Test
    public void clearCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItems());
        when(cartRepository.save(any())).thenReturn(emptyCart());

        assertDoesNotThrow(() -> cartService.clearCart(CART_ID));
    }

    @Test
    public void applyCouponToCartTest_HappyPath() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItem());
        when(couponQueryService.getCoupon(any())).thenReturn(coupon1());
        when(cartRepository.save(any())).thenReturn(cartWithOneItemAndAppliedCoupon());

        assertDoesNotThrow(() -> cartService.applyCouponToCart(CART_ID, COUPON_1_CODE));

        verify(cartQueryService, times(1)).getCart(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void applyCouponToCartTest_UnhappyPath_CouponExpiredException() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItem());
        when(couponQueryService.getCoupon(any())).thenReturn(coupon3());

        var result = assertThrows(
                CouponExpiredException.class,
                () -> cartService.applyCouponToCart(CART_ID, COUPON_1_CODE)
        );

        assertThat(result.getMessage(), equalTo("Coupon with the given code 'SPRING15' has expired."));

        verify(cartQueryService, times(1)).getCart(any());
        verify(couponQueryService, times(1)).getCoupon(any());
        verify(cartRepository, never()).save(any());
    }

    @Test
    public void removeCouponFromCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItemAndAppliedCoupon());
        when(cartRepository.save(any())).thenReturn(cartWithTwoItems());

        assertDoesNotThrow(() -> cartService.removeCouponFromCart(CART_ID));
    }
}
