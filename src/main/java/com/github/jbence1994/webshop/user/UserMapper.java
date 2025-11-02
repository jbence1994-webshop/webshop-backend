package com.github.jbence1994.webshop.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "membershipTier", expression = "java(profile.getMembershipTier().name())")
    ProfileDto toProfileDto(Profile profile);

    AddressDto toAddressDto(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "profileAvatar", ignore = true)
    @Mapping(target = "coupons", ignore = true)
    User toUser(RegistrationRequest.UserDto user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "avatarFileName", ignore = true)
    @Mapping(target = "loyaltyPoints", ignore = true)
    @Mapping(target = "rewardPoints", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    Profile toProfile(RegistrationRequest.ProfileDto profile);

    @Mapping(target = "profileId", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toAddress(RegistrationRequest.AddressDto address);
}
