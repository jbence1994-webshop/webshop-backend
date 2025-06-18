package com.github.jbence1994.webshop.order;

import java.util.List;

public interface OrderQueryService {
    List<Order> getOrders();

    Order getOrder(Long id);
}
