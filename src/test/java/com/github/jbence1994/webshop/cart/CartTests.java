package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

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
    private final Cart cart = CartTestObject.cartWithTwoItems();
    private final Cart emptyCart = CartTestObject.emptyCart();

    @Test
    public void calculateTotalPriceTest() {
        var result = cart.calculateTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(139.98)));
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

    @Test
    public void clearTest_HappyPath_CartIsEmpty() {
        cart.clear();

        assertThat(cart.getItems(), is(empty()));
    }

    // TODO: Refactor to parameterized test.
    @Test
    public void isEmptyTest_CartIsNotEmpty() {
        var result = cart.isEmpty();

        assertThat(result, is(false));
    }

    @Test
    public void isEmptyTest_CartIsEmpty() {
        var result = emptyCart.isEmpty();

        assertThat(result, is(true));
    }
}
