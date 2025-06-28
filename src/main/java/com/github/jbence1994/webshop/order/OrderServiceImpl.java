package com.github.jbence1994.webshop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
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
    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

    private void save(Order order) {
        orderRepository.save(order);
    }
}
