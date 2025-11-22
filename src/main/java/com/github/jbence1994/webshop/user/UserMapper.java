package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    AddressDto toDto(Address address);

    RegistrationResponse toRegistrationResponse(User user);

    WishlistProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "avatarFileName", ignore = true)
    @Mapping(target = "coupons", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    User toEntity(RegistrationRequest.UserDto user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toEntity(RegistrationRequest.AddressDto address);
}
