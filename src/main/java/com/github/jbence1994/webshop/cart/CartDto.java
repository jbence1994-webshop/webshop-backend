package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class CartDto {
    private UUID id;
    private List<CartItemDto> items = new ArrayList<>();
    private String appliedCoupon;
    private BigDecimal totalPrice = BigDecimal.ZERO;
}
