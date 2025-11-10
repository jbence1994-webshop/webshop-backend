package com.github.jbence1994.webshop.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    ProfileDto toProfileDto(Profile profile);

    AddressDto toAddressDto(Address address);

    RegistrationResponse toRegistrationResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "profileAvatar", ignore = true)
    User toUser(RegistrationRequest.UserDto user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "avatarFileName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "coupons", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    Profile toProfile(RegistrationRequest.ProfileDto profile);

    @Mapping(target = "profileId", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toAddress(RegistrationRequest.AddressDto address);
}
