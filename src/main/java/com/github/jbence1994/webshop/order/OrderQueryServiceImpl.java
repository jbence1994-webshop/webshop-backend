package com.github.jbence1994.webshop.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        // FIXME: Later refactor this to get all orders of the current, logged in user.
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        // FIXME: Later refactor this to check the current, logged in user can access an order ot not.
        // FIXME: Just the ones that the user itself performed.

        return orderRepository
                .getOrderWithItems(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
