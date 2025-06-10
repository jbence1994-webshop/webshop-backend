package com.github.jbence1994.webshop.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
