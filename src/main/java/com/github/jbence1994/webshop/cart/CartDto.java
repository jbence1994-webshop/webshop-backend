package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class CartDto {
    private UUID id;
    private List<CartItemDto> items;
    private String appliedCoupon;
    private BigDecimal totalPrice;
}
