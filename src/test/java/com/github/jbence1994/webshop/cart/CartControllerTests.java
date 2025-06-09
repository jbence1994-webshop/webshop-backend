package com.github.jbence1994.webshop.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static com.github.jbence1994.webshop.cart.CartDtoTestObject.emptyCartDto;
import static com.github.jbence1994.webshop.cart.CartTestObject.emptyCart;
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
                hasProperty("totalPrice", equalTo(emptyCart().getTotalPrice()))
        ));
        assertThat(result.getBody().getItems(), is(empty()));
    }
}
