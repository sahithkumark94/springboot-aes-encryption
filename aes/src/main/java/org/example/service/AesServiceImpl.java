package org.example.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

@Service
public class AesServiceImpl implements AesService{

    private static final String key = "ABCEOH1JIEiprtMM";
    private static final String IV = "ABCEOH1JIEiprtMM";

    public String encryption(String plainText, String type){
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            if (type.equals("param")) {
                return URLEncoder.encode(Base64.getEncoder().encodeToString(encrypted), "UTF-8" );
            }
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String decrypt(String ciphertext, String type){
        try {
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            if(type.equals("param")) {
                ciphertext = URLDecoder.decode(ciphertext, "UTF-8");
            }
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}


