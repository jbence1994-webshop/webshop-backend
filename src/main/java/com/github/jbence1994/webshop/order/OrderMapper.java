package com.github.jbence1994.webshop.order;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
