package com.video.live.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.video.live.common.core.SpringContextHolder;
import com.video.live.common.properties.JWTProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * JWT token工具类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/16 14:25
 */
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    private static final JWTProperties properties = SpringContextHolder.getBean(JWTProperties.class);

    /**
     * 生成 token
     *
     * @param value payload 中声明的用户名
     * @return token
     */
    public static String generate(String value) {
        return generate(properties.getClaimKey(), value);
    }

    /**
     * 生成token
     *
     * @param key   payload 中的key
     * @param value payload 的value
     * @return token
     */
    public static String generate(String key, String value) {
        return JWT.create()
                .withClaim(key, value)
                .withExpiresAt(new Date(System.currentTimeMillis() + properties.getExpiresTime()))
                .sign(Algorithm.HMAC256(properties.getSalt()));
    }

    /**
     * 验证token的有效性
     *
     * @param token 用户token
     * @return 是否有效
     */
    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(properties.getSalt()))
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            logger.info("无效的token");
            return false;
        }
        return true;
    }

    /**
     * 从token中获取payload 信息
     *
     * @param token token
     * @return payload 信息
     */
    public static String getUserName(String token) {
        try {
            return JWT.decode(token)
                    .getClaim(properties.getClaimKey())
                    .asString();
        } catch (JWTVerificationException e) {
            logger.warn("无效的token");
        }
        return null;
    }
}
