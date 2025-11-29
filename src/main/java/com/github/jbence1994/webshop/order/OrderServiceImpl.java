package com.github.jbence1994.webshop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderQueryService orderQueryService;
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        save(order);
    }

    @Override
    public void updateOrder(Order order) {
        save(order);
    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        var order = orderQueryService.getOrder(id);

        var currentStatus = order.getStatus();

        if (OrderStatus.CREATED.equals(currentStatus) ||
                OrderStatus.CANCELED.equals(currentStatus) ||
                OrderStatus.FAILED.equals(currentStatus) ||
                OrderStatus.REFUNDED.equals(currentStatus)
        ) {
            throw new InvalidOrderStatusException(currentStatus);
        }

        if (OrderStatus.CONFIRMED.equals(currentStatus) && !OrderStatus.SHIPPED.equals(status)) {
            throw new InvalidOrderStatusException(currentStatus, status);
        }

        if (OrderStatus.SHIPPED.equals(currentStatus) && !OrderStatus.DELIVERED.equals(status)) {
            throw new InvalidOrderStatusException(currentStatus, status);
        }

        if (OrderStatus.DELIVERED.equals(currentStatus) && !OrderStatus.RETURNED.equals(status)) {
            throw new InvalidOrderStatusException(currentStatus, status);
        }

        if (OrderStatus.RETURNED.equals(currentStatus) && !OrderStatus.REFUNDED.equals(status)) {
            throw new InvalidOrderStatusException(currentStatus, status);
        }

        order.setStatus(status);

        save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void save(Order order) {
        orderRepository.save(order);
    }
}
