package com.github.jbence1994.webshop.user;

public interface UserService {
    User registerUser(User user);

    void changePassword(Long userId, String oldPassword, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);
}
