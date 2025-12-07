package com.github.jbence1994.webshop.user;

import java.time.LocalDate;

public interface DecryptedUserTestConstants {
    String DECRYPTED_EMAIL_1 = "juhasz.bence.zsolt@gmail.com";
    String DECRYPTED_EMAIL_2 = "juhasz.bence@outlook.hu";
    String RAW_PASSWORD = "12345678";
    String RAW_INVALID_PASSWORD = "123456789";
    String RAW_OLD_PASSWORD = RAW_PASSWORD;
    String RAW_INVALID_OLD_PASSWORD = "123456789";
    String RAW_NEW_PASSWORD = RAW_INVALID_PASSWORD;
    String RAW_CONFIRM_PASSWORD = RAW_PASSWORD;
    String RAW_CONFIRM_NEW_PASSWORD = RAW_NEW_PASSWORD;
    String RAW_INVALID_CONFIRM_PASSWORD = "0123456789";
    String RAW_INVALID_CONFIRM_NEW_PASSWORD = "0123456789";
    String DECRYPTED_FIRST_NAME = "Bence";
    String DECRYPTED_MIDDLE_NAME = "Zsolt";
    String DECRYPTED_LAST_NAME = "Juhász";
    LocalDate DECRYPTED_DATE_OF_BIRTH = LocalDate.of(1994, 3, 27);
    String DECRYPTED_PHONE_NUMBER = "+36501323566";
}
