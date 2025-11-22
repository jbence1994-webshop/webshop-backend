package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserIdentityMapper {

    UserIdentity toUserIdentity(User user);
}
