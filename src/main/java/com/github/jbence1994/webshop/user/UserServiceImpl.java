package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordManager passwordManager;

    @Override
    public User registerUser(User user) {
        var email = user.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        user.setPassword(passwordManager.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }
}
