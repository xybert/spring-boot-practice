package com.xybert.springbootsecurity;

import com.xybert.springbootsecurity.util.PBKDF2Util;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author xybert
 * @description PBKDF2加密算法测试类
 * @date 2023/01/17 10:34
 */

public class PBKDF2Test {

    @Test
    public void testPBKDF2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = "admin123";
        String salt = PBKDF2Util.generateSalt();
        System.out.println(salt);
        System.out.println(PBKDF2Util.generateEncryptedPassword(password, salt));
    }
}
