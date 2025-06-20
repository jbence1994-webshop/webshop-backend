package com.github.jbence1994.webshop.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void createOrder(Order order) {
        orderRepository.save(order);
    }
}
