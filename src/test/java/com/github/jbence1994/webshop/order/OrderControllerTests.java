package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.github.jbence1994.webshop.order.OrderDtoTestObject.orderDto;
import static com.github.jbence1994.webshop.order.OrderTestObject.createdOrder1;
import static com.github.jbence1994.webshop.order.UpdateOrderStatusRequestTestObject.updateOrderStatusRequest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

    @Mock
    private OrderQueryService orderQueryService;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void getOrdersTest() {
        when(orderQueryService.getOrders()).thenReturn(List.of(createdOrder1()));
        when(orderMapper.toDto(any())).thenReturn(orderDto());

        var result = orderController.getOrders();

        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void getOrderTest() {
        when(orderQueryService.getOrder(any())).thenReturn(createdOrder1());
        when(orderMapper.toDto(any())).thenReturn(orderDto());

        var result = orderController.getOrder(1L);

        assertThat(result.id(), equalTo(orderDto().id()));
        assertThat(result.totalPrice(), equalTo(orderDto().totalPrice()));
        assertThat(result.discountAmount(), equalTo(orderDto().discountAmount()));
        assertThat(result.status(), equalTo(orderDto().status()));
        assertThat(result.createdAt(), equalTo(orderDto().createdAt()));
    }

    @Test
    public void updateOrderStatusTest() {
        doNothing().when(orderService).updateOrderStatus(any(), any());

        var result = orderController.updateOrderStatus(1L, updateOrderStatusRequest());

        assertThat(result.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
        assertThat(result.getBody(), is(nullValue()));
    }
}
