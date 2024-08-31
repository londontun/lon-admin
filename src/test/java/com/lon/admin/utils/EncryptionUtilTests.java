package com.lon.admin.utils;

import org.junit.jupiter.api.Test;

/**
 * 加密工具类测试
 */
public class EncryptionUtilTests {

    @Test
    void test() {
        String password = "123456";
        String salt = EncryptionUtil.generateSalt();
        String encrypt = EncryptionUtil.encrypt(password, salt);
        assert EncryptionUtil.matches(password, salt, encrypt);
    }
}
