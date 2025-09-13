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
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private void save(Order order) {
        orderRepository.save(order);
    }
}
