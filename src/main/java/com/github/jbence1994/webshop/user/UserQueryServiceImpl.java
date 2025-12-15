package com.github.jbence1994.webshop.user;

import com.github.jbence1994.webshop.auth.AuthService;
import com.github.jbence1994.webshop.common.CryptoService;
import com.github.jbence1994.webshop.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final CryptoService cryptoService;
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

        var decryptedBillingAddress = userDecrypter.decrypt(encryptedUser.getBillingAddress(), cryptoService);
        var decryptedShippingAddress = userDecrypter.decrypt(encryptedUser.getShippingAddress(), cryptoService);
        var decryptedUser = userDecrypter.decrypt(encryptedUser, cryptoService);

        decryptedBillingAddress.setUser(decryptedUser);
        decryptedShippingAddress.setUser(decryptedUser);
        decryptedUser.setBillingAddress(decryptedBillingAddress);
        decryptedUser.setShippingAddress(decryptedShippingAddress);

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
