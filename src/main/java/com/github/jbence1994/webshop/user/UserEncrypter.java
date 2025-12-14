package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.common.CryptoService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEncrypter {

    @Mapping(
            target = "email",
            expression = "java(cryptoService.encrypt(user.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(cryptoService.encrypt(user.getFirstName()))"
    )
    @Mapping(
            target = "middleName",
            expression = "java(user.getMiddleName() == null ? null : cryptoService.encrypt(user.getMiddleName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(cryptoService.encrypt(user.getLastName()))"
    )
    @Mapping(
            target = "dateOfBirth",
            expression = "java(cryptoService.encrypt(user.getDateOfBirth().toString()))"
    )
    @Mapping(
            target = "phoneNumber",
            expression = "java(cryptoService.encrypt(user.getPhoneNumber()))"
    )
    @Mapping(
            target = "avatarFileName",
            expression = "java(user.getAvatarFileName() == null ? null : cryptoService.encrypt(user.getAvatarFileName()))"
    )
    @Mapping(
            target = "billingAddress",
            ignore = true
    )
    @Mapping(
            target = "shippingAddress",
            ignore = true
    )
    EncryptedUser encrypt(DecryptedUser user, @Context CryptoService cryptoService);

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(cryptoService.encrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(cryptoService.encrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(cryptoService.encrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(cryptoService.encrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(cryptoService.encrypt(address.getCountry()))"
    )
    EncryptedBillingAddress encrypt(DecryptedBillingAddress address, @Context CryptoService cryptoService);

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(cryptoService.encrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(cryptoService.encrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(cryptoService.encrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(cryptoService.encrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(cryptoService.encrypt(address.getCountry()))"
    )
    EncryptedShippingAddress encrypt(DecryptedShippingAddress address, @Context CryptoService cryptoService);
}
