package org.example.service;

public interface AesService {

    String encryption(String plainText, String type);

    String decrypt(String ciphertext, String type);
}
