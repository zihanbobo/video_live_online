package com.video.live.common.util;

import cn.hutool.crypto.symmetric.AES;

import java.nio.charset.StandardCharsets;

/**
 * 加解密工具类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/10 16:40
 */
public class SecureUtils {

    /**
     * 密钥必须16位
     */
    private static final String KEY = "1234567891234567";

    /**
     * AES 加密
     *
     * @param pwd 明文密码
     * @param key 密钥
     * @return 密文
     */
    public static String encryptAES(String pwd, String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] pwdBytes = pwd.getBytes(StandardCharsets.UTF_8);
        AES aes = new AES(keyBytes);
        return aes.encryptBase64(pwdBytes);
    }

    /**
     * AES 解密
     * @param cipherText 密文
     * @param key 密钥
     * @return 明文
     */
    public static String decryptAES(String cipherText, String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] cipherTextBytes = cipherText.getBytes(StandardCharsets.UTF_8);
        AES aes = new AES(keyBytes);
        return aes.decryptStr(cipherTextBytes);
    }

}
