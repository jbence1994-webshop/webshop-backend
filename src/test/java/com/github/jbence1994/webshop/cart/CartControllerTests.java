package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.cart.AddItemToCartRequestTestObject.addItemToCartRequest;
import static com.github.jbence1994.webshop.cart.CartDtoTestObject.cartDto;
import static com.github.jbence1994.webshop.cart.CartDtoTestObject.emptyCartDto;
import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.cartItemDto;
import static com.github.jbence1994.webshop.cart.CartItemDtoTestObject.updatedCartItemDto;
import static com.github.jbence1994.webshop.cart.CartItemTestObject.cartItem;
import static com.github.jbence1994.webshop.cart.CartItemTestObject.updatedCartItem;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithOneItem;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.cart.UpdateCartItemRequestTestObject.updateCartItemRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTests {

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private CartService cartService;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartController cartController;

    @Test
    public void createCartTest() {
        when(cartService.createCart()).thenReturn(emptyCart());
        when(cartMapper.toDto(any(Cart.class))).thenReturn(emptyCartDto());

        var result = cartController.createCart();

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("id", equalTo(emptyCart().getId())),
                hasProperty("totalPrice", equalTo(emptyCart().calculateTotalPrice()))
        ));
        assertThat(result.getBody().getItems(), is(empty()));
    }

    @Test
    public void addProductToCartTest() {
        when(cartService.addProductToCart(any(), anyLong())).thenReturn(cartItem());
        when(cartMapper.toDto(any(CartItem.class))).thenReturn(cartItemDto());

        var result = cartController.addProductToCart(CART_ID, addItemToCartRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("quantity", equalTo(cartItemDto().getQuantity())),
                hasProperty("totalPrice", equalTo(cartItemDto().getTotalPrice()))
        ));
    }

    @Test
    public void getCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithOneItem());
        when(cartMapper.toDto(any(Cart.class))).thenReturn(cartDto());

        var result = cartController.getCart(CART_ID);

        assertThat(result, allOf(
                hasProperty("totalPrice", equalTo(cartDto().getTotalPrice()))
        ));
    }

    @Test
    public void updateCartItemTest() {
        when(cartService.updateCartItem(any(), anyLong(), anyInt())).thenReturn(updatedCartItem());
        when(cartMapper.toDto(any(CartItem.class))).thenReturn(updatedCartItemDto());

        var result = cartController.updateCartItem(CART_ID, 1L, updateCartItemRequest());

        assertThat(result, allOf(
                hasProperty("quantity", equalTo(updatedCartItemDto().getQuantity())),
                hasProperty("totalPrice", equalTo(updatedCartItemDto().getTotalPrice()))
        ));
    }

    @Test
    public void deleteCartItemTest() {
        doNothing().when(cartService).deleteCartItem(any(), any());

        var result = cartController.deleteCartItem(CART_ID, 1L);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }

    @Test
    public void clearCartTest() {
        doNothing().when(cartService).clearCart(any());

        var result = cartController.clearCart(CART_ID);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
