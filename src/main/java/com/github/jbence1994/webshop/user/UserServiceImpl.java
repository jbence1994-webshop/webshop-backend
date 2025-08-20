package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserQueryService userQueryService;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Override
    public User registerUser(User user) {
        var email = user.getEmail();
        var phoneNumber = user.getPhoneNumber();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        if (userRepository.existsByProfilePhoneNumber(phoneNumber)) {
            throw new PhoneNumberAlreadyExistsException(phoneNumber);
        }

        user.setPassword(passwordManager.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        return user;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        var user = authService.getCurrentUser();

        if (!passwordManager.verify(oldPassword, user.getPassword())) {
            throw new AccessDeniedException("Invalid old password.");
        }

        user.setPassword(passwordManager.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String email) {
        var user = userQueryService.getUser(email);

        // TODO:
        //  1. Revoke password in the database
        //  2. Generate a temp. password for log in and save it to database (with time and rate limit)
        //  3. Force user to change password
        //  4. Remove temp. password from database
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
