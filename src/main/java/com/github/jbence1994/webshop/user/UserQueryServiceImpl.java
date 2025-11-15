package com.github.jbence1994.webshop.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUser(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
