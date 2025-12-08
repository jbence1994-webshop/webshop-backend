package com.github.jbence1994.webshop.user;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEncrypter {

    @Mapping(
            target = "email",
            expression = "java(aesCryptoService.encrypt(user.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(aesCryptoService.encrypt(user.getFirstName()))"
    )
    @Mapping(
            target = "middleName",
            expression = "java(user.getMiddleName() == null ? null : aesCryptoService.encrypt(user.getMiddleName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(aesCryptoService.encrypt(user.getLastName()))"
    )
    @Mapping(
            target = "dateOfBirth",
            expression = "java(aesCryptoService.encrypt(user.getDateOfBirth().toString()))"
    )
    @Mapping(
            target = "phoneNumber",
            expression = "java(aesCryptoService.encrypt(user.getPhoneNumber()))"
    )
    @Mapping(
            target = "avatarFileName",
            expression = "java(user.getAvatarFileName() == null ? null : aesCryptoService.encrypt(user.getAvatarFileName()))"
    )
    @Mapping(
            target = "billingAddress",
            ignore = true
    )
    @Mapping(
            target = "shippingAddress",
            ignore = true
    )
    EncryptedUser encrypt(DecryptedUser user, @Context AesCryptoService aesCryptoService);

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(aesCryptoService.encrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(aesCryptoService.encrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(aesCryptoService.encrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(aesCryptoService.encrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(aesCryptoService.encrypt(address.getCountry()))"
    )
    EncryptedBillingAddress encrypt(DecryptedBillingAddress address, @Context AesCryptoService aesCryptoService);

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(aesCryptoService.encrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(aesCryptoService.encrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(aesCryptoService.encrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(aesCryptoService.encrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(aesCryptoService.encrypt(address.getCountry()))"
    )
    EncryptedShippingAddress encrypt(DecryptedShippingAddress address, @Context AesCryptoService aesCryptoService);
}
