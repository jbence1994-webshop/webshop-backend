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
    public Profile createProfile(Long userId, Profile profile) {
        var user = userQueryService.getUser(userId);

        user.setProfile(profile);
        userRepository.save(user);

        return profile;
    }

    @Override
    public Address createAddress(Long userId, Address address) {
        var user = userQueryService.getUser(address.getProfileId());

        user.getProfile().setAddress(address);
        userRepository.save(user);

        return address;
    }

    // TODO: Later add option to change password when User is not logged in: e.g.: forgot password scenario.
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

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
