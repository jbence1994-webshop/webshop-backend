package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.github.jbence1994.webshop.order.OrderTestObject.order;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderQueryServiceImplTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderQueryServiceImpl orderQueryService;

    @Test
    public void getOrdersTest() {
        when(orderRepository.findAll()).thenReturn(List.of(order()));

        var result = orderQueryService.getOrders();

        assertThat(result, not(empty()));
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getOrderTest_HappyPath() {
        when(orderRepository.getOrderWithItems(any())).thenReturn(Optional.of(order()));

        var result = assertDoesNotThrow(() -> orderQueryService.getOrder(1L));

        assertThat(result, not(nullValue()));
        assertThat(result, allOf(
                hasProperty("id", equalTo(order().getId())),
                hasProperty("totalPrice", equalTo(order().getTotalPrice())),
                hasProperty("status", equalTo(order().getStatus())),
                hasProperty("createdAt", equalTo(order().getCreatedAt()))
        ));
    }

    @Test
    public void getOrderTest_UnhappyPath_OrderNotFound() {
        when(orderRepository.getOrderWithItems(any())).thenReturn(Optional.empty());

        var result = assertThrows(
                OrderNotFoundException.class,
                () -> orderQueryService.getOrder(1L)
        );

        assertThat(result.getMessage(), equalTo("No order was found with the given ID: #1."));
    }
}
