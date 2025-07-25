package com.github.jbence1994.webshop.order;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {
    private final OrderRepository orderRepository;
    private final AuthService authService;

    @Override
    public List<Order> getOrders() {
        var user = authService.getCurrentUser();

        return orderRepository.findAllByCustomer(user.getId());
    }

    @Override
    public Order getOrder(Long id) {
        var order = orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        var user = authService.getCurrentUser();

        if (!order.isPlacedBy(user.getEmail())) {
            throw new AccessDeniedException("Access denied.");
        }

        return order;
    }
}
