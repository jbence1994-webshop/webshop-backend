package com.github.jbence1994.webshop.user;

public interface UserService {
    User registerUser(User user);

    void changePassword(String oldPassword, String newPassword);

    void forgotPassword(String email);

    String verifyRecoveryCode(String email, String recoveryCode);

    void resetPassword(String resetToken, String newPassword);

    void updateUser(User user);

    void deleteUser(Long id);
}
