package com.github.jbence1994.webshop.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final ChangePasswordRequestSanitizer changePasswordRequestSanitizer;
    private final ForgotPasswordRequestSanitizer forgotPasswordRequestSanitizer;
    private final ResetPasswordRequestSanitizer resetPasswordRequestSanitizer;
    private final RegistrationRequestSanitizer registrationRequestSanitizer;
    private final UserQueryService userQueryService;
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        var user = userQueryService.getDecryptedUser(id);

        return userMapper.toDto(user);
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationRequest request) {
        var sanitizedRequest = registrationRequestSanitizer.sanitize(request);

        var address = userMapper.toEntity(sanitizedRequest.user().address());
        var user = userMapper.toEntity(sanitizedRequest.user());

        address.setUser(user);
        user.setAddress(address);

        userService.registerUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        var sanitizedRequest = changePasswordRequestSanitizer.sanitize(request);

        userService.changePassword(sanitizedRequest.oldPassword(), sanitizedRequest.newPassword());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        var sanitizedRequest = forgotPasswordRequestSanitizer.sanitize(request);

        userService.forgotPassword(sanitizedRequest.email());

        return ResponseEntity.accepted().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        var sanitizedRequest = resetPasswordRequestSanitizer.sanitize(request);

        userService.resetPassword(sanitizedRequest.temporaryPassword(), sanitizedRequest.newPassword());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/wishlist")
    public List<WishlistProductDto> getWishlist() {
        var wishlist = userQueryService.getWishlist();

        return wishlist.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping("/wishlist")
    public ResponseEntity<WishlistProductDto> addProductToWishlist(@Valid @RequestBody AddProductToWishlistRequest request) {
        var product = userService.addProductToWishlist(request.productId());

        var wishlistProductDto = userMapper.toDto(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistProductDto);
    }

    @DeleteMapping("/wishlist")
    public ResponseEntity<Void> deleteProductFromWishlist(@Valid @RequestBody DeleteProductFromWishlistRequest request) {
        userService.deleteProductFromWishlist(request.productId());

        return ResponseEntity.noContent().build();
    }
}
