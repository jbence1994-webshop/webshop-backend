package com.github.jbence1994.webshop.user;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class AesCryptoService {

    @Value("${webshop.encryption.aes-secret-key}")
    private String aesSecretKey;

    @SneakyThrows
    public String encrypt(String raw) {
        var secretKeySpec = new SecretKeySpec(aesSecretKey.getBytes(), "AES");

        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        return Base64.getEncoder().encodeToString(cipher.doFinal(raw.getBytes()));
    }

    @SneakyThrows
    public String decrypt(String encrypted) {
        var secretKeySpec = new SecretKeySpec(aesSecretKey.getBytes(), "AES");

        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
