package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;
import com.github.jbence1994.webshop.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FakeAuthService implements AuthService {
    private final UserQueryService userQueryService;

    @Override
    public User getCurrentUser() {
        return userQueryService.getUser(1L);
    }
}
