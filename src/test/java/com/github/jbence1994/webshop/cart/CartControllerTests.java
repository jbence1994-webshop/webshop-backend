package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.product.ProductQueryService;
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
import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static com.github.jbence1994.webshop.cart.CartTestObject.cart1;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
import static com.github.jbence1994.webshop.product.ProductTestObject.product1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartControllerTests {

    @Mock
    private CartQueryService cartQueryService;

    @Mock
    private ProductQueryService productQueryService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    @InjectMocks
    private CartController cartController;

    @Test
    public void createCartTest() {
        when(cartRepository.save(any())).thenReturn(emptyCart());
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
    public void addToCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(emptyCart());
        when(productQueryService.getProduct(any())).thenReturn(product1());
        when(cartRepository.save(any())).thenReturn(cart1());
        when(cartMapper.toDto(any(CartItem.class))).thenReturn(cartItemDto());

        var result = cartController.addToCart(CART_ID, addItemToCartRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody(), allOf(
                hasProperty("quantity", equalTo(cartItemDto().getQuantity())),
                hasProperty("totalPrice", equalTo(cartItemDto().getTotalPrice()))
        ));
    }

    @Test
    public void getCartTest() {
        when(cartQueryService.getCart(any())).thenReturn(cart1());
        when(cartMapper.toDto(any(Cart.class))).thenReturn(cartDto());

        var result = cartController.getCart(CART_ID);

        assertThat(result, allOf(
                hasProperty("totalPrice", equalTo(cartDto().getTotalPrice()))
        ));
    }
}
