package com.github.jbence1994.webshop.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
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
        var user = userQueryService.getUser(id);

        return userMapper.toUserDto(user);
    }

    @PostMapping
    public ResponseEntity<RegistrationResponse> registerUser(@Valid @RequestBody RegistrationRequest request) {
        var sanitizedRequest = registrationRequestSanitizer.sanitize(request);

        var address = userMapper.toAddress(sanitizedRequest.user().address());
        var user = userMapper.toUser(sanitizedRequest.user());

        address.setUser(user);
        user.setAddress(address);

        var registeredUser = userService.registerUser(user);

        var response = userMapper.toRegistrationResponse(registeredUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
}
