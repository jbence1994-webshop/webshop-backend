package com.github.jbence1994.webshop.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserQueryService userQueryService;
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        var user = userQueryService.getUser(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        var user = userMapper.toEntity(request);

        var registeredUser = userService.registerUser(user);

        var registerUserResponse = new RegisterUserResponse(registeredUser.getId(), request.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserResponse);
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<CreateProfileResponse> createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateProfileRequest request
    ) {
        var profile = userMapper.toEntity(request);

        var createdProfile = userService.createProfile(userId, profile);

        var createProfileResponse = new CreateProfileResponse(
                createdProfile.getFirstName(),
                createdProfile.getMiddleName(),
                createdProfile.getLastName(),
                createdProfile.getDateOfBirth(),
                createdProfile.getPhoneNumber(),
                createdProfile.getLoyaltyPoints()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createProfileResponse);
    }

    @PostMapping("/{userId}/address")
    public ResponseEntity<AddressDto> createAddress(
            @PathVariable Long userId,
            @Valid @RequestBody AddressDto addressDto
    ) {
        var address = userMapper.toEntity(addressDto);

        userService.createAddress(userId, address);

        return ResponseEntity.status(HttpStatus.CREATED).body(addressDto);
    }

    @PostMapping("/{userId}")
    public void changePassword(
            @PathVariable Long userId,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        userService.changePassword(
                userId,
                request.getOldPassword(),
                request.getNewPassword()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
