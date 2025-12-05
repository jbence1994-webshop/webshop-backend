package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final AesCryptoService aesCryptoService;
    private final UserRepository userRepository;
    private final UserDecrypter userDecrypter;
    private final AuthService authService;

    @Override
    public EncryptedUser getEncryptedUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public DecryptedUser getDecryptedUser(Long id) {
        var encryptedUser = getEncryptedUser(id);

        var decryptedAddress = userDecrypter.decrypt(encryptedUser.getAddress(), aesCryptoService);
        var decryptedUser = userDecrypter.decrypt(encryptedUser, aesCryptoService);

        decryptedAddress.setUser(decryptedUser);
        decryptedUser.setAddress(decryptedAddress);

        return decryptedUser;
    }

    @Override
    public EncryptedUser getUser(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<Product> getWishlist() {
        var user = authService.getCurrentUser();

        return user.getFavoriteProducts();
    }
}
