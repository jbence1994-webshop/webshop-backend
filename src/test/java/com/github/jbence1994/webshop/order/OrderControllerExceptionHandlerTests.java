package com.github.jbence1994.webshop.order;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

@ExtendWith(MockitoExtension.class)
public class OrderControllerExceptionHandlerTests {

    @InjectMocks
    private OrderControllerExceptionHandler orderControllerExceptionHandler;

    private static Stream<Arguments> invalidOrderStatusExceptionTestParams() {
        return Stream.of(
                Arguments.of(
                        Named.of("InvalidOrderStatusException#1", new InvalidOrderStatusException(OrderStatus.CREATED)),
                        "Order with status: CREATED cannot changed to anything."
                ),
                Arguments.of(
                        Named.of("InvalidOrderStatusException#2", new InvalidOrderStatusException(OrderStatus.CONFIRMED, OrderStatus.RETURNED)),
                        "Order with status: CONFIRMED cannot changed to RETURNED."
                )
        );
    }

    @Test
    public void handleOrderNotFoundExceptionTest() {
        var result = orderControllerExceptionHandler.handleOrderNotFoundException(new OrderNotFoundException(1L));

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo("No order was found with the given ID: #1."));
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidOrderStatusExceptionTestParams")
    public void handleInvalidOrderStatusExceptionTest(
            InvalidOrderStatusException exception,
            String errorMessage
    ) {
        var result = orderControllerExceptionHandler.handleInvalidOrderStatusException(exception);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), not(nullValue()));
        assertThat(result.getBody().error(), equalTo(errorMessage));
    }
}
