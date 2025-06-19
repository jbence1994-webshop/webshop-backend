package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserIdentityMapper {

    @Mapping(target = "firstName", source = "profile.firstName")
    @Mapping(target = "middleName", source = "profile.middleName")
    @Mapping(target = "lastName", source = "profile.lastName")
    UserIdentity toUserIdentity(User user);
}
