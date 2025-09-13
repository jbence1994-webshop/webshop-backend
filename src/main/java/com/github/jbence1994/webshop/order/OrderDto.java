package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        BigDecimal totalPrice,
        BigDecimal discountAmount,
        String status,
        LocalDateTime createdAt,
        List<OrderItemDto> items
) {
}
