package com.github.jbence1994.webshop.user;

public interface UserService {
    User registerUser(User user);

    void changePassword(String oldPassword, String newPassword);

    void forgotPassword(String email);

    void resetPassword(String temporaryPassword, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);
}
