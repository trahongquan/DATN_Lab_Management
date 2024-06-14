package LabManagement.ClassSuport;

import java.math.BigInteger;
import java.security.SecureRandom;

/** Chưa dùng tới */

public class SecretKeyGenerator {
    public String SecretKeyGenerator() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        String secretKey = new BigInteger(1, keyBytes).toString(16);
        System.out.println("Secret Key: " + secretKey);
        return secretKey;
    }
}