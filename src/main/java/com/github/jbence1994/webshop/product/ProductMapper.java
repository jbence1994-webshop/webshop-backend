package com.github.jbence1994.webshop.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "category", source = "category.name")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDto productDto);
}
