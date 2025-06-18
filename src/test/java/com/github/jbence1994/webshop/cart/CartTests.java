package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItemAndFixedAmountTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItemAndPercentOffTypeOfAppliedCoupon;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class CartTests {
    private final Cart cart = cartWithTwoItems();
    private final Cart cartWithFixedAmountTypeOfAppliedCoupon = cartWithOneItemAndFixedAmountTypeOfAppliedCoupon();
    private final Cart cartWithPercentOffTypeOfAppliedCoupon = cartWithOneItemAndPercentOffTypeOfAppliedCoupon();
    private final Cart emptyCart = emptyCart();

    private static Stream<Arguments> cartEmptyCheckParams() {
        return Stream.of(
                Arguments.of("Cart is not empty", cartWithTwoItems(), false),
                Arguments.of("Cart is empty", emptyCart(), true)
        );
    }

    private static Stream<Arguments> cartHasCouponAppliedParams() {
        return Stream.of(
                Arguments.of("Cart has coupon applied", cartWithOneItemAndFixedAmountTypeOfAppliedCoupon(), true),
                Arguments.of("Cart does not have a coupon applied", cartWithTwoItems(), false)
        );
    }

    @Test
    public void getItemTest_HappyPath_CartItemIsNotNull() {
        var result = cart.getItem(1L);

        assertThat(result, not(nullValue()));
        assertThat(result.getProduct().getId(), equalTo(1L));
    }

    @Test
    public void getItemTest_UnhappyPath_CartItemIsNull() {
        var result = cart.getItem(3L);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void addItemTest_HappyPath_CartIsNotEmpty_NewItemToCart() {
        cart.addItem(product2());

        assertThat(cart.getItems().size(), equalTo(2));
    }

    @Test
    public void addItemTest_HappyPath_CartIsNotEmpty_AlreadyAddedItemToCart() {
        cart.addItem(product2());

        assertThat(cart.getItems().size(), equalTo(2));
    }

    @Test
    public void addItemTest_HappyPath_CartIsEmpty_NewItemToCart() {
        emptyCart.addItem(product1());

        assertThat(emptyCart.getItems().size(), equalTo(1));
    }

    @Test
    public void removeItemTest_HappyPath_CartIsNotEmpty() {
        cart.removeItem(1L);
        cart.removeItem(2L);

        assertThat(cart.getItems(), is(empty()));
    }

    @Test
    public void removeItemTest_HappyPath_CartIsEmpty() {
        emptyCart.removeItem(1L);

        assertThat(emptyCart.getItems(), is(empty()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("cartHasCouponAppliedParams")
    public void hasCouponAppliedTests(
            String testCase,
            Cart cart,
            boolean expectedResult
    ) {
        var result = cart.hasCouponApplied();

        assertThat(result, is(expectedResult));
    }

    @Test
    public void clearTest_HappyPath_CartIsEmpty() {
        cart.clear();

        assertThat(cart.getItems(), is(empty()));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("cartEmptyCheckParams")
    public void isEmptyTests(
            String testCase,
            Cart cart,
            boolean expectedResult
    ) {
        var result = cart.isEmpty();

        assertThat(result, is(expectedResult));
    }

    @Test
    public void calculateTotalPriceTest_DoesNotHaveCouponApplied() {
        var result = cart.calculateTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(139.98)));
    }

    @Test
    public void calculateTotalPriceTest_HasFixedAmountTypeOfCouponApplied() {
        var result = cartWithFixedAmountTypeOfAppliedCoupon.calculateTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(44.99)));
    }

    @Test
    public void calculateTotalPriceTest_HasPercentOffTypeOfCouponApplied() {
        var result = cartWithPercentOffTypeOfAppliedCoupon.calculateTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(44.99)));
    }
}
