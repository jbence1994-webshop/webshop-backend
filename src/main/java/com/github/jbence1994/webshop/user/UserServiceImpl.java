package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.EmailService;
import com.github.jbence1994.webshop.common.EmailTemplateBuilder;
import com.github.jbence1994.webshop.common.WebshopEmailAddressConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final WebshopEmailAddressConfig webshopEmailAddressConfig;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final RecoveryCodeGenerator recoveryCodeGenerator;
    private final EmailTemplateBuilder emailTemplateBuilder;
    private final ResetTokenGenerator resetTokenGenerator;
    private final UserQueryService userQueryService;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    private final EmailService emailService;
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
    @Transactional
    public void forgotPassword(String email) {
        var user = userQueryService.getUser(email);

        var rawRecoveryCode = recoveryCodeGenerator.generate();
        var hashedRecoveryCode = passwordManager.encode(rawRecoveryCode);

        recoveryCodeRepository.save(new RecoveryCode(user, hashedRecoveryCode));

        var emailContent = emailTemplateBuilder.buildForForgotPassword(
                user.getFirstName(),
                rawRecoveryCode,
                Locale.ENGLISH
        );

        emailService.sendEmail(
                webshopEmailAddressConfig.username(),
                email,
                emailContent.subject(),
                emailContent.body()
        );
    }

    @Override
    public String verifyRecoveryCode(String email, String recoveryCode) {
        var currentUser = authService.getCurrentUser();

        var isExistingRecoveryCode = recoveryCodeRepository.exists(recoveryCode);
        var isExistingRecoveryCodeByUser = recoveryCodeRepository.existsByUserId(currentUser.getId());

        if (!isExistingRecoveryCode && !isExistingRecoveryCodeByUser) {
            throw new InvalidRecoveryCodeException();
        }

        return resetTokenGenerator.generate();
    }

    @Override
    public void resetPassword(String recoveryCode, String newPassword) {
        var user = authService.getCurrentUser();

        var recoveryCodes = recoveryCodeRepository.findAllByUserId(user.getId());

        var latestRecoveryCode = recoveryCodeRepository
                .findTopByUserIdOrderByExpirationDateDesc(user.getId())
                .orElseThrow(InvalidRecoveryCodeException::new);

        var recoveryCodesWithoutLatest = new ArrayList<>(recoveryCodes);
        recoveryCodesWithoutLatest.removeIf(code -> code.getId().equals(latestRecoveryCode.getId()));

        recoveryCodeRepository.deleteAll(recoveryCodesWithoutLatest);

        if (!passwordManager.verify(recoveryCode, user.getPassword())) {
            throw new AccessDeniedException("Invalid recovery code.");
        }

        if (latestRecoveryCode.isExpired()) {
            recoveryCodeRepository.delete(latestRecoveryCode);
            throw new ExpiredRecoveryCodeException();
        }

        user.setPassword(passwordManager.encode(newPassword));
        userRepository.save(user);

        recoveryCodeRepository.delete(latestRecoveryCode);
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
