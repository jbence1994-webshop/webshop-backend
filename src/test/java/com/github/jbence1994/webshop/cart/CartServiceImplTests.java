package com.github.jbence1994.webshop.cart;

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
import static com.github.jbence1994.webshop.cart.CartTestObject.cartWithTwoItems;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.cart.CartTestObject.updatedCart;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTests {

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private CartQueryService cartQueryService;

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
    public void addItemToCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(emptyCart());
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(cartRepository.save(any())).thenReturn(cartWithOneItem());

        var result = cartService.addItemToCart(CART_ID, 1L);

        assertThat(result.getQuantity(), equalTo(cartItem().getQuantity()));
        assertThat(result.calculateSubTotal(), equalTo(cartItem().calculateSubTotal()));
    }

    @Test
    public void updateCartItemTest() {
        when(cartQueryService.getCart(any())).thenReturn(cartWithTwoItems());
        when(cartQueryService.getCartItem(any(), any())).thenReturn(cartItem());
        when(cartRepository.save(any())).thenReturn(updatedCart());

        var result = cartService.updateCartItem(CART_ID, 1L, 2);

        assertThat(result.getQuantity(), equalTo(updatedCartItem().getQuantity()));
        assertThat(result.calculateSubTotal(), equalTo(updatedCartItem().calculateSubTotal()));
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

    /*@Test
    public void deleteCartTest() {
        doNothing().when(cartRepository).deleteById(any());

        assertDoesNotThrow(() -> cartService.deleteCart(CART_ID));
    }*/
}
