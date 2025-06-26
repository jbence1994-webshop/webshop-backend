package com.github.jbence1994.webshop.user;

public interface UserService {
    User registerUser(User user);

    Profile createProfile(Long userId, Profile profile);

    Address createAddress(Long userId, Address address);

    void changePassword(Long userId, String oldPassword, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);
}
