package com.github.jbence1994.webshop.user;

import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserQueryService userQueryService;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;

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

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        var user = userQueryService.getUser(userId);

        if (!passwordManager.verify(oldPassword, user.getPassword())) {
            throw new AccessDeniedException("Invalid old password.");
        }

        user.setPassword(passwordManager.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
