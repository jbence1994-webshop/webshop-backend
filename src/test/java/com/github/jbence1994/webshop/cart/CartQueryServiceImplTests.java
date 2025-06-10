package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cart;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartQueryServiceImplTests {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartQueryServiceImpl cartQueryService;

    @Test
    public void getCartTest_HappyPath() {
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart()));

        var result = assertDoesNotThrow(() -> cartQueryService.getCart(CART_ID));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(cart().getId())),
                hasProperty("createdAt", equalTo(cart().getCreatedAt()))
        ));
        assertThat(result.getItems(), not(empty()));
    }

    @Test
    public void getCartTest_UnhappyPath_CartNotFoundException() {
        when(cartRepository.findById(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                CartNotFoundException.class,
                () -> cartQueryService.getCart(CART_ID)
        );

        assertThat(result.getMessage(), equalTo("No cart was found with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69."));
    }

    @Test
    public void getCartItemTest_HappyPath() {
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart()));

        var result = assertDoesNotThrow(() -> cartQueryService.getCartItem(CART_ID, 1L));

        assertThat(result, not(nullValue()));
        assertThat(result.getProduct().getId(), equalTo(1L));
        assertThat(result.getQuantity(), equalTo(1));
    }

    @Test
    public void getCartItemTest_UnhappyPath_CartItemNotFoundException() {
        when(cartRepository.findById(any())).thenReturn(Optional.of(cart()));

        var result = assertThrows(
                CartItemNotFoundException.class,
                () -> cartQueryService.getCartItem(CART_ID, 100L)
        );

        assertThat(result.getMessage(), equalTo("No cart item was found with the given product ID: 100."));
    }
}
