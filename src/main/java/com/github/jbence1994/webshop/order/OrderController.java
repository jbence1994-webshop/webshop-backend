package com.github.jbence1994.webshop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderQueryService orderQueryService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public List<OrderDto> getOrders() {
        return orderQueryService.getOrders().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Long id) {
        var order = orderQueryService.getOrder(id);

        return orderMapper.toDto(order);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrderStatus(
            @PathVariable("id") Long id,
            @RequestBody OrderStatus orderStatus
    ) {
        var order = orderService.updateOrderStatus(id, orderStatus);

        return orderMapper.toDto(order);
    }
}
