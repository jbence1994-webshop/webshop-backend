package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.cart.CartItemTestObject.cartItem;
import static com.github.jbence1994.webshop.cart.CartItemTestObject.updatedCartItem;
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cart;
import static com.github.jbence1994.webshop.cart.CartTestObject.updatedCart;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTests {

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void updateItemTest() {
        when(cartQueryService.getCart(any())).thenReturn(cart());
        when(cartQueryService.getCartItem(any(), any())).thenReturn(cartItem());
        when(cartRepository.save(any())).thenReturn(updatedCart());

        var result = cartService.updateCartItemQuantity(CART_ID, 1L, 2);

        assertThat(result.getQuantity(),equalTo(updatedCartItem().getQuantity()));
    }
}
