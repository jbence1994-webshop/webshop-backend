package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.coupon.Coupon;
import com.github.jbence1994.webshop.coupon.UserCouponDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "coupons", expression = "java(getNotExpiredCoupons(user.getCoupons()))")
    UserDto toUserDto(User user);

    // TODO: Need to filter that a non-expired coupon is already redeemed in any order or not.
    default List<UserCouponDto> getNotExpiredCoupons(List<Coupon> coupons) {
        return coupons.stream()
                .filter(coupon -> !coupon.isExpired())
                .map(coupon -> new UserCouponDto(coupon.getCode(), coupon.getExpirationDate()))
                .toList();
    }

    ProfileDto toProfileDto(Profile profile);

    AddressDto toAddressDto(Address address);

    RegistrationResponse toRegistrationResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "profileAvatar", ignore = true)
    @Mapping(target = "coupons", ignore = true)
    @Mapping(target = "favoriteProducts", ignore = true)
    User toUser(RegistrationRequest.UserDto user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "avatarFileName", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Profile toProfile(RegistrationRequest.ProfileDto profile);

    @Mapping(target = "profileId", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "country", expression = "java(address.country().toUpperCase())")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toAddress(RegistrationRequest.AddressDto address);
}
