package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.EmailConfig;
import com.github.jbence1994.webshop.common.EmailService;
import com.github.jbence1994.webshop.common.EmailTemplateBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TemporaryPasswordRepository temporaryPasswordRepository;
    private final TemporaryPasswordGenerator temporaryPasswordGenerator;
    private final EmailTemplateBuilder emailTemplateBuilder;
    private final UserQueryService userQueryService;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthService authService;
    private final EmailConfig emailConfig;

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
    @Transactional
    public void forgotPassword(String email) {
        var user = userQueryService.getUser(email);

        var rawTemporaryPassword = temporaryPasswordGenerator.generate();
        var hashedTemporaryPassword = passwordManager.encode(rawTemporaryPassword);

        var temporaryPassword = new TemporaryPassword(
                hashedTemporaryPassword,
                user,
                LocalDateTime.now().plusMinutes(15)
        );
        temporaryPasswordRepository.save(temporaryPassword);

        user.setPassword(hashedTemporaryPassword);

        var emailContent = emailTemplateBuilder.buildForForgotPassword(
                user.getFirstName(),
                rawTemporaryPassword,
                Locale.ENGLISH
        );
        emailService.sendEmail(
                emailConfig.username(),
                email,
                emailContent.subject(),
                emailContent.body()
        );
    }

    @Override
    public void resetPassword(String rawTemporaryPassword, String newPassword) {
        var user = authService.getCurrentUser();

        if (!passwordManager.verify(rawTemporaryPassword, user.getPassword())) {
            throw new AccessDeniedException("Invalid temporary password.");
        }

        var temporaryPassword = temporaryPasswordRepository
                .findByPassword(user.getPassword())
                .orElseThrow(InvalidTemporaryPasswordException::new);

        if (temporaryPassword.isExpired()) {
            temporaryPasswordRepository.delete(temporaryPassword);
            throw new ExpiredTemporaryPasswordException();
        }

        temporaryPasswordRepository.delete(temporaryPassword);

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
