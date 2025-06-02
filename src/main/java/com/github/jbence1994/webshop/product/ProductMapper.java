package com.github.jbence1994.webshop.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Product toEntity(CreateProductDto productDto);
}
