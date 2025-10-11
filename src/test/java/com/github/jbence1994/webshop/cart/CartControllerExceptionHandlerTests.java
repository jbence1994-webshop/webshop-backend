package com.github.jbence1994.webshop.cart;

import com.github.jbence1994.webshop.product.ProductNotFoundException;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.cart.CartTestConstants.CART_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class CartControllerExceptionHandlerTests {

    @InjectMocks
    private CartControllerExceptionHandler cartControllerExceptionHandler;

    private static Stream<Arguments> cartOrCartItemNotFoundExceptionParams() {
        return Stream.of(
                Arguments.of(
                        Named.of("CartNotFoundException", new CartNotFoundException(CART_ID)),
                        "No cart was found with the given ID: 00492884-e657-4c6a-abaa-aef8f4240a69."
                ),
                Arguments.of(
                        Named.of("CartItemNotFoundException", new CartItemNotFoundException(100L)),
                        "No cart item was found with the given product ID: 100."
                )
        );
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("cartOrCartItemNotFoundExceptionParams")
    public void handleCartOrCartItemNotFoundExceptionTests(RuntimeException exception, String expectedExceptionMessage) {
        var result = cartControllerExceptionHandler.handleCartOrCartItemNotFoundException(exception);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo(expectedExceptionMessage));
    }

    @Test
    public void handleProductNotFoundExceptionTest() {
        var result = cartControllerExceptionHandler.handleProductNotFoundException(new ProductNotFoundException(1L));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No product was found with the given ID: #1."));
    }
}
