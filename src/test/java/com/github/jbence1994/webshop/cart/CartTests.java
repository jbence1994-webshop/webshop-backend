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

@ExtendWith(MockitoExtension.class)
public class CartTests {
    private final Cart cart1 = CartTestObject.cart1();
    private final Cart cart2 = CartTestObject.cart2();
    private final Cart emptyCart = CartTestObject.emptyCart();

    @Test
    public void getTotalPriceTest() {
        var result = cart2.getTotalPrice();

        assertThat(result, equalTo(BigDecimal.valueOf(139.98)));
    }

    @Test
    public void addItemTest_HappyPath_CartIsNotEmpty_NewItemToCart() {
        cart1.addItem(product2());

        assertThat(cart1.getItems().size(), equalTo(2));
    }

    @Test
    public void addItemTest_HappyPath_CartIsNotEmpty_AlreadyAddedItemToCart() {
        cart2.addItem(product2());

        assertThat(cart2.getItems().size(), equalTo(2));
    }

    @Test
    public void addItemTest_HappyPath_CartIsEmpty_NewItemToCart() {
        emptyCart.addItem(product1());

        assertThat(emptyCart.getItems().size(), equalTo(1));
    }

    @Test
    public void removeItemTest_HappyPath_CartIsNotEmpty() {
        cart1.removeItem(1L);

        assertThat(cart1.getItems(), is(empty()));
    }

    @Test
    public void removeItemTest_HappyPath_CartIsEmpty() {
        emptyCart.removeItem(1L);

        assertThat(emptyCart.getItems(), is(empty()));
    }

    @Test
    public void clearTest_HappyPath_CartIsEmpty() {
        cart1.clear();

        assertThat(cart1.getItems(), is(empty()));
    }

    @Test
    public void isEmptyTest_CartIsNotEmpty() {
        var result = cart1.isEmpty();

        assertThat(result, is(false));
    }

    @Test
    public void isEmptyTest_CartIsEmpty() {
        var result = emptyCart.isEmpty();

        assertThat(result, is(true));
    }
}
