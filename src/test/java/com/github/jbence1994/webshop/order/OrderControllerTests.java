package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.jbence1994.webshop.order.OrderDtoTestObject.orderDto;
import static com.github.jbence1994.webshop.order.OrderTestObject.order;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

    @Mock
    private OrderQueryService orderQueryService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void getOrdersTest() {
        when(orderQueryService.getOrders()).thenReturn(List.of(order()));
        when(orderMapper.toDto(any())).thenReturn(orderDto());

        var result = orderController.getOrders();

        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getOrderTest() {
        when(orderQueryService.getOrder(any())).thenReturn(order());
        when(orderMapper.toDto(any())).thenReturn(orderDto());

        var result = orderController.getOrder(1L);

        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDto().getId())),
                hasProperty("totalPrice", equalTo(orderDto().getTotalPrice())),
                hasProperty("status", equalTo(orderDto().getStatus())),
                hasProperty("createdAt", equalTo(orderDto().getCreatedAt()))
        ));
    }
}
