package com.github.jbence1994.webshop.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
    private BigDecimal discountAmount;
    private BigDecimal shippingCost;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
}
