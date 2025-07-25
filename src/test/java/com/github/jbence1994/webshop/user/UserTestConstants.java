package com.github.jbence1994.webshop.user;

public interface UserTestConstants {
    String EMAIL = "juhasz.bence.zsolt@gmail.com";
    String ANOTHER_EMAIL = "juhasz.bence@outlook.hu";
    String PASSWORD = "12345678";
    String INVALID_PASSWORD = "123456789";
    String OLD_PASSWORD = PASSWORD;
    String INVALID_OLD_PASSWORD = "123456789";
    String NEW_PASSWORD = INVALID_PASSWORD;
    String HASHED_PASSWORD = "$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6a";
    String NEW_HASHED_PASSWORD = "$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6b";
    String ANOTHER_HASHED_PASSWORD = "$2a$10$OnASmWOv6fF/voWlTQNfSOm20Fh4AaPgTDVTwPrMiF0FTjYEWzb6c";
    String CONFIRM_PASSWORD = PASSWORD;
    String CONFIRM_NEW_PASSWORD = NEW_PASSWORD;
    String INVALID_CONFIRM_PASSWORD = "0123456789";
    String INVALID_CONFIRM_NEW_PASSWORD = "0123456789";
}
