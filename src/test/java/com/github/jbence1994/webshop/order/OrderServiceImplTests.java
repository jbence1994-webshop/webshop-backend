package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static com.github.jbence1994.webshop.order.OrderTestObject.canceledOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.confirmedOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.deliveredOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.failedOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.refundedOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.returnedOrder1;
import static com.github.jbence1994.webshop.order.OrderTestObject.shippedOrder1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTests {

    @Mock
    private OrderQueryService orderQueryService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private static Stream<Arguments> updateOrderStatusHappyPathTestParams() {
        return Stream.of(
                Arguments.of(Named.of("From CONFIRMED to SHIPPED", confirmedOrder1()), OrderStatus.SHIPPED),
                Arguments.of(Named.of("From SHIPPED to DELIVERED", shippedOrder1()), OrderStatus.DELIVERED),
                Arguments.of(Named.of("From DELIVERED to RETURNED", deliveredOrder1()), OrderStatus.RETURNED),
                Arguments.of(Named.of("From RETURNED to REFUNDED", returnedOrder1()), OrderStatus.REFUNDED)
        );
    }

    private static Stream<Arguments> updateOrderStatusUnhappyPathTestParams() {
        return Stream.of(
                Arguments.of(Named.of("From CONFIRMED to DELIVERED", confirmedOrder1()), OrderStatus.DELIVERED, "Order with status: CONFIRMED cannot changed to DELIVERED."),
                Arguments.of(Named.of("From SHIPPED to RETURNED", shippedOrder1()), OrderStatus.RETURNED, "Order with status: SHIPPED cannot changed to RETURNED."),
                Arguments.of(Named.of("From DELIVERED to CONFIRMED", deliveredOrder1()), OrderStatus.CONFIRMED, "Order with status: DELIVERED cannot changed to CONFIRMED."),
                Arguments.of(Named.of("From RETURNED to SHIPPED", returnedOrder1()), OrderStatus.SHIPPED, "Order with status: RETURNED cannot changed to SHIPPED.")
        );
    }

    private static Stream<Arguments> updateOrderStatusUnhappyPathNonChangeableStateTestParams() {
        return Stream.of(
                Arguments.of(Named.of("CREATED", createdOrder1()), "Order with status: CREATED cannot changed to anything."),
                Arguments.of(Named.of("CANCELED", canceledOrder1()), "Order with status: CANCELED cannot changed to anything."),
                Arguments.of(Named.of("FAILED", failedOrder1()), "Order with status: FAILED cannot changed to anything."),
                Arguments.of(Named.of("REFUNDED", refundedOrder1()), "Order with status: REFUNDED cannot changed to anything.")
        );
    }

    @Test
    public void createOrderTest() {
        when(orderRepository.save(any())).thenReturn(createdOrder1());

        assertDoesNotThrow(() -> orderService.createOrder(createdOrder1()));

        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void updateOrderTest() {
        when(orderRepository.save(any())).thenReturn(confirmedOrder1());

        assertDoesNotThrow(() -> orderService.updateOrder(createdOrder1()));

        verify(orderRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateOrderStatusHappyPathTestParams")
    public void updateOrderStatusTest_HappyPaths(
            Order order,
            OrderStatus status
    ) {
        when(orderQueryService.getOrder(any())).thenReturn(order);

        assertDoesNotThrow(() -> orderService.updateOrderStatus(1L, status));

        verify(orderRepository, times(1)).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateOrderStatusUnhappyPathTestParams")
    public void updateOrderStatusTest_UnhappyPaths(
            Order order,
            OrderStatus status,
            String exceptionMessage
    ) {
        when(orderQueryService.getOrder(any())).thenReturn(order);

        var result = assertThrows(
                InvalidOrderStatusException.class,
                () -> orderService.updateOrderStatus(1L, status)
        );

        assertThat(result.getMessage(), equalTo(exceptionMessage));

        verify(orderRepository, never()).save(any());
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateOrderStatusUnhappyPathNonChangeableStateTestParams")
    public void updateOrderStatusTest_UnhappyPaths_NonChangeableStates(
            Order order,
            String exceptionMessage
    ) {
        when(orderQueryService.getOrder(any())).thenReturn(order);

        var result = assertThrows(
                InvalidOrderStatusException.class,
                () -> orderService.updateOrderStatus(1L, order.getStatus())
        );

        assertThat(result.getMessage(), equalTo(exceptionMessage));

        verify(orderRepository, never()).save(any());
    }

    @Test
    public void deleteCartTest() {
        doNothing().when(orderRepository).deleteById(any());

        assertDoesNotThrow(() -> orderService.deleteOrder(1L));

        verify(orderRepository, times(1)).deleteById(any());
    }
}
