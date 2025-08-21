package com.github.jbence1994.webshop.user;

public interface UserService {
    User registerUser(User user);

    void changePassword(String oldPassword, String newPassword);

    void resetPassword(String email);

    void confirmResetPassword(String temporaryPassword, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);
}
