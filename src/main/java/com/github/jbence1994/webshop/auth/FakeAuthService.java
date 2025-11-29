package com.github.jbence1994.webshop.auth;

import com.github.jbence1994.webshop.user.User;
import com.github.jbence1994.webshop.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FakeAuthService implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        return userRepository.findById(1L).get();
    }
}
