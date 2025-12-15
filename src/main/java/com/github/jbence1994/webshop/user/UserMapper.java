package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(DecryptedUser user);

    BillingAddressDto toDto(DecryptedBillingAddress address);

    ShippingAddressDto toDto(DecryptedShippingAddress address);

    WishlistProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "avatarFileName", ignore = true)
    @Mapping(target = "loyaltyPoints", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    DecryptedUser toEntity(RegistrationRequest.UserDto user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DecryptedBillingAddress toEntity(RegistrationRequest.BillingAddressDto address);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DecryptedShippingAddress toEntity(RegistrationRequest.ShippingAddressDto address);
}
