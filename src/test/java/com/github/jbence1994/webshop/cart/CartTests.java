package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static com.github.jbence1994.webshop.product.ProductTestObject.product2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class CartTests {
    private final Cart cart = cartWithTwoItems();
    private final Cart emptyCart = emptyCart();

    private static Stream<Arguments> cartEmptyCheckParams() {
        return Stream.of(
                Arguments.of("Cart is not empty", cartWithTwoItems(), false),
                Arguments.of("Cart is empty", emptyCart(), true)
        );
    }

    @Test
    public void getItemTest_HappyPath_CartItemIsPresent() {
        var result = cart.getItem(1L);

        assertThat(result.isPresent(), is(true));
        assertThat(result.get().getProduct().getId(), equalTo(1L));
    }

    @Test
    public void getItemTest_UnhappyPath_CartItemIsEmpty() {
        var result = cart.getItem(3L);

        assertThat(result.isEmpty(), is(true));
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
    public void calculateTotalTest() {
        var result = cart.calculateTotal();

        assertThat(result, comparesEqualTo(BigDecimal.valueOf(139.98)));
    }

    @Test
    public void mapCartItemsToOrderItemsTest() {
        var result = cart.mapCartItemsToOrderItems();

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(2));
    }
}
