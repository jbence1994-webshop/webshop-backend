package com.github.jbence1994.webshop.common;

public interface CryptoService {

    String encrypt(String raw);

    String decrypt(String encrypted);
}
