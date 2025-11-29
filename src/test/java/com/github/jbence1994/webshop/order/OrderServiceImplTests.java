package com.github.jbence1994.webshop.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.jbence1994.webshop.order.OrderTestObject.order1;
import static com.github.jbence1994.webshop.order.OrderTestObject.updatedOrder1;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void createOrderTest() {
        when(orderRepository.save(any())).thenReturn(order1());

        assertDoesNotThrow(() -> orderService.createOrder(order1()));

        verify(orderRepository).save(any());
    }

    @Test
    public void updateOrderTest() {
        when(orderRepository.save(any())).thenReturn(updatedOrder1());

        assertDoesNotThrow(() -> orderService.updateOrder(order1()));

        verify(orderRepository).save(any());
    }

    @Test
    public void updateOrderStatusTest() {
        when(orderQueryService.getOrder(any())).thenReturn(updatedOrder1());

        assertDoesNotThrow(() -> orderService.updateOrderStatus(1L, OrderStatus.SHIPPED));

        verify(orderRepository).save(any());
    }

    @Test
    public void deleteCartTest() {
        doNothing().when(orderRepository).deleteById(any());

        assertDoesNotThrow(() -> orderService.deleteOrder(1L));

        verify(orderRepository).deleteById(any());
    }
}
