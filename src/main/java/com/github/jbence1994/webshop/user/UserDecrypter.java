package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.common.CryptoService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UserDecrypter {

    @Mapping(
            target = "email",
            expression = "java(cryptoService.decrypt(user.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(cryptoService.decrypt(user.getFirstName()))"
    )
    @Mapping(
            target = "middleName",
            expression = "java(user.getMiddleName() == null ? null : cryptoService.decrypt(user.getMiddleName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(cryptoService.decrypt(user.getLastName()))"
    )
    @Mapping(
            target = "dateOfBirth",
            qualifiedByName = "decryptDateOfBirth"
    )
    @Mapping(
            target = "phoneNumber",
            expression = "java(cryptoService.decrypt(user.getPhoneNumber()))"
    )
    @Mapping(
            target = "avatarFileName",
            expression = "java(user.getAvatarFileName() == null ? null : cryptoService.decrypt(user.getAvatarFileName()))"
    )
    @Mapping(
            target = "billingAddress",
            ignore = true
    )
    @Mapping(
            target = "shippingAddress",
            ignore = true
    )
    DecryptedUser decrypt(EncryptedUser user, @Context CryptoService cryptoService);

    @Named("decryptDateOfBirth")
    default LocalDate decryptDateOfBirth(String dateOfBirth, @Context CryptoService cryptoService) {
        return LocalDate.parse(cryptoService.decrypt(dateOfBirth));
    }

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(cryptoService.decrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(cryptoService.decrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(cryptoService.decrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(cryptoService.decrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(cryptoService.decrypt(address.getCountry()))"
    )
    DecryptedBillingAddress decrypt(EncryptedBillingAddress address, @Context CryptoService cryptoService);

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(cryptoService.decrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(cryptoService.decrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(cryptoService.decrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(cryptoService.decrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(cryptoService.decrypt(address.getCountry()))"
    )
    DecryptedShippingAddress decrypt(EncryptedShippingAddress address, @Context CryptoService cryptoService);
}
