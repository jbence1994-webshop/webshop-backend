package com.github.jbence1994.webshop.order;

public interface OrderService {
    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Long id);
}
