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
import static org.hamcrest.Matchers.equalTo;
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

        assertThat(result.id(), equalTo(orderDto().id()));
        assertThat(result.totalPrice(), equalTo(orderDto().totalPrice()));
        assertThat(result.discountAmount(), equalTo(orderDto().discountAmount()));
        assertThat(result.shippingCost(), equalTo(orderDto().shippingCost()));
        assertThat(result.status(), equalTo(orderDto().status()));
        assertThat(result.createdAt(), equalTo(orderDto().createdAt()));
    }
}
