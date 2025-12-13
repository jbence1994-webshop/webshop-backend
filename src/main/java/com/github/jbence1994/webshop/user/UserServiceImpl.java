package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.EmailService;
import com.github.jbence1994.webshop.common.EmailTemplateBuilder;
import com.github.jbence1994.webshop.common.WebshopEmailAddressConfig;
import com.github.jbence1994.webshop.product.Product;
import com.github.jbence1994.webshop.product.ProductAlreadyOnWishlistException;
import com.github.jbence1994.webshop.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TemporaryPasswordRepository temporaryPasswordRepository;
    private final TemporaryPasswordGenerator temporaryPasswordGenerator;
    private final WebshopEmailAddressConfig webshopEmailAddressConfig;
    private final EmailTemplateBuilder emailTemplateBuilder;
    private final ProductQueryService productQueryService;
    private final UserQueryService userQueryService;
    private final AesCryptoService aesCryptoService;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;
    private final UserEncrypter userEncrypter;
    private final EmailService emailService;
    private final AuthService authService;

    @Override
    public void registerUser(DecryptedUser user) {
        var encryptedBillingAddress = userEncrypter.encrypt(user.getBillingAddress(), aesCryptoService);
        var encryptedShippingAddress = userEncrypter.encrypt(user.getShippingAddress(), aesCryptoService);
        var encryptedUser = userEncrypter.encrypt(user, aesCryptoService);

        encryptedBillingAddress.setUser(encryptedUser);
        encryptedShippingAddress.setUser(encryptedUser);
        encryptedUser.setBillingAddress(encryptedBillingAddress);
        encryptedUser.setShippingAddress(encryptedShippingAddress);

        if (userRepository.existsByEmail(encryptedUser.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (userRepository.existsByPhoneNumber(encryptedUser.getPhoneNumber())) {
            throw new PhoneNumberAlreadyExistsException();
        }

        encryptedUser.setPassword(passwordManager.hash(user.getPassword()));
        encryptedUser.setRole(Role.USER);

        userRepository.save(encryptedUser);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        var user = authService.getCurrentUser();

        if (!passwordManager.verify(oldPassword, user.getPassword())) {
            throw new AccessDeniedException("Invalid old password.");
        }

        user.setPassword(passwordManager.hash(newPassword));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void forgotPassword(String email) {
        var encryptedEmail = aesCryptoService.encrypt(email);

        var user = userQueryService.getUser(encryptedEmail);

        var rawTemporaryPassword = temporaryPasswordGenerator.generate();
        var hashedTemporaryPassword = passwordManager.hash(rawTemporaryPassword);

        temporaryPasswordRepository.save(new TemporaryPassword(hashedTemporaryPassword, user));

        user.setPassword(hashedTemporaryPassword);

        var emailContent = emailTemplateBuilder.buildForForgotPassword(
                aesCryptoService.decrypt(user.getFirstName()),
                rawTemporaryPassword,
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
    public void resetPassword(String temporaryPassword, String newPassword) {
        var user = authService.getCurrentUser();

        var temporaryPasswords = temporaryPasswordRepository.findAllByUserId(user.getId());

        var latestTemporaryPassword = temporaryPasswordRepository
                .findTopByUserIdOrderByExpirationDateDesc(user.getId())
                .orElseThrow(InvalidTemporaryPasswordException::new);

        var temporaryPasswordsWithoutLatest = new ArrayList<>(temporaryPasswords);
        temporaryPasswordsWithoutLatest.removeIf(tempPassword -> tempPassword.getId().equals(latestTemporaryPassword.getId()));

        temporaryPasswordRepository.deleteAll(temporaryPasswordsWithoutLatest);

        if (!passwordManager.verify(temporaryPassword, user.getPassword())) {
            throw new AccessDeniedException("Invalid temporary password.");
        }

        if (latestTemporaryPassword.isExpired()) {
            temporaryPasswordRepository.delete(latestTemporaryPassword);
            throw new ExpiredTemporaryPasswordException();
        }

        user.setPassword(passwordManager.hash(newPassword));
        userRepository.save(user);

        temporaryPasswordRepository.delete(latestTemporaryPassword);
    }

    @Override
    public void updateUser(EncryptedUser user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Product addProductToWishlist(Long productId) {
        try {
            var user = authService.getCurrentUser();
            var product = productQueryService.getProduct(productId);

            user.addFavoriteProduct(product);

            updateUser(user);

            return product;
        } catch (Exception exception) {
            throw new ProductAlreadyOnWishlistException(productId);
        }
    }

    @Override
    public void deleteProductFromWishlist(Long productId) {
        var user = authService.getCurrentUser();

        user.removeFavoriteProduct(productId);

        updateUser(user);
    }
}
