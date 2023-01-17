package com.xybert.springbootsecurity.util;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Objects;

/**
 * @author xybert
 * @description PBKDF2工具类
 * @date 2023/01/16 17:50
 */

@Component
public class PBKDF2Util {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int SALT_BYTE_SIZE = 32 / 2;    // 盐的长度
    public static final int HASH_BIT_SIZE = 128 * 4;    // 生成密文的长度
    public static final int PBKDF2_ITERATIONS = 1000;   // 迭代次数

    /**
     * 验证输入的密码
     *
     * @param attemptedPassword 待验证密码
     * @param encryptedPassword 密文
     * @param salt 盐值
     */
    public static boolean authenticate(String attemptedPassword, String encryptedPassword, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 用相同的盐值对用户输入的密码进行加密
        String encryptedAttemptedPassword = generateEncryptedPassword(attemptedPassword, salt);
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return Objects.equals(encryptedAttemptedPassword, encryptedPassword);
    }

    /**
     * 生成密文
     *
     * @param password 明文密码
     * @param salt 盐值
     */
    public static String generateEncryptedPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] saltBytes = DatatypeConverter.parseHexBinary(salt);
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), saltBytes, PBKDF2_ITERATIONS, HASH_BIT_SIZE);
        SecretKeyFactory secretKey = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return DatatypeConverter.printHexBinary(secretKey.generateSecret(keySpec).getEncoded());
    }

    /**
     * 通过加密的强随机数生成盐
     *
     * @return String
     */
    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        return DatatypeConverter.printHexBinary(salt);
    }
}
