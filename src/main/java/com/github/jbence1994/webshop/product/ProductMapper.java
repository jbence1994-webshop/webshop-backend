package com.github.jbence1994.webshop.product;

import com.github.jbence1994.webshop.image.ImageUrlBuilder;
import com.github.jbence1994.webshop.image.ProductPhoto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    CategoryDto toDto(Category category);

    @Mapping(target = "url", expression = "java(imageUrlBuilder.buildUrl(productPhoto.getFileName()))")
    ProductPhotoDto toDto(ProductPhoto productPhoto, @Context ImageUrlBuilder imageUrlBuilder);

    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "photo", ignore = true)
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    Product toEntity(ProductDto productDto);
}
