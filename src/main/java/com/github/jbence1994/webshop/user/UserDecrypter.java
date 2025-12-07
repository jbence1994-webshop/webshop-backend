package com.github.jbence1994.webshop.user;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface UserDecrypter {

    @Mapping(
            target = "email",
            expression = "java(aesCryptoService.decrypt(user.getEmail()))"
    )
    @Mapping(
            target = "firstName",
            expression = "java(aesCryptoService.decrypt(user.getFirstName()))"
    )
    @Mapping(
            target = "middleName",
            expression = "java(user.getMiddleName() == null ? null : aesCryptoService.decrypt(user.getMiddleName()))"
    )
    @Mapping(
            target = "lastName",
            expression = "java(aesCryptoService.decrypt(user.getLastName()))"
    )
    @Mapping(
            target = "dateOfBirth",
            qualifiedByName = "decryptDateOfBirth"
    )
    @Mapping(
            target = "phoneNumber",
            expression = "java(aesCryptoService.decrypt(user.getPhoneNumber()))"
    )
    @Mapping(
            target = "avatarFileName",
            expression = "java(user.getAvatarFileName() == null ? null : aesCryptoService.decrypt(user.getAvatarFileName()))"
    )
    @Mapping(
            target = "address",
            ignore = true
    )
    DecryptedUser decrypt(EncryptedUser user, @Context AesCryptoService aesCryptoService);

    @Named("decryptDateOfBirth")
    default LocalDate decryptDateOfBirth(String dateOfBirth, @Context AesCryptoService aesEncryptionService) {
        return LocalDate.parse(aesEncryptionService.decrypt(dateOfBirth));
    }

    @Mapping(
            target = "user",
            ignore = true
    )
    @Mapping(
            target = "addressLine",
            expression = "java(aesCryptoService.decrypt(address.getAddressLine()))"
    )
    @Mapping(
            target = "municipality",
            expression = "java(aesCryptoService.decrypt(address.getMunicipality()))"
    )
    @Mapping(
            target = "province",
            expression = "java(aesCryptoService.decrypt(address.getProvince()))"
    )
    @Mapping(
            target = "postalCode",
            expression = "java(aesCryptoService.decrypt(address.getPostalCode()))"
    )
    @Mapping(
            target = "country",
            expression = "java(aesCryptoService.decrypt(address.getCountry()))"
    )
    DecryptedAddress decrypt(EncryptedAddress address, @Context AesCryptoService aesCryptoService);
}
