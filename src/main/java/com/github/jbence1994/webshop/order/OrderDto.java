package com.github.jbence1994.webshop.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        BigDecimal totalPrice,
        BigDecimal payableTotalPrice,
        BigDecimal discountAmount,
        BigDecimal shippingCost,
        String status,
        LocalDateTime createdAt,
        List<OrderItemDto> items
) {
}
