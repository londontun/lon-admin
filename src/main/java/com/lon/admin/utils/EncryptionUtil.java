package com.lon.admin.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * 加密工具类
 */
public class EncryptionUtil {

    /**
     * 生成盐
     *
     * @return 盐
     */
    public static String generateSalt() {
        return RandomUtil.randomString(16);
    }

    /**
     * 加密
     *
     * @param rawPassword 原始密码
     * @param salt        盐
     * @return 加密后的密码
     */
    public static String encrypt(String rawPassword, String salt) {
        String saltedPassword = rawPassword + salt;
        return DigestUtil.bcrypt(saltedPassword);
    }

    /**
     * 验证
     *
     * @param rawPassword       原始密码
     * @param salt              盐
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String salt, String encryptedPassword) {
        String saltedPassword = rawPassword + salt;
        return DigestUtil.bcryptCheck(saltedPassword, encryptedPassword);
    }
}
