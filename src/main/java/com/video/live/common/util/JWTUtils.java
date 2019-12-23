package com.video.live.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.video.live.common.core.SpringContextHolder;
import com.video.live.common.properties.JwtProperties;
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
    private static final JwtProperties properties = SpringContextHolder.getBean(JwtProperties.class);

    /**
     * 生成token
     *
     * @param value payload 声明的value
     * @return token
     */
    public static String generate(String value) {
        return JWT.create()
                .withSubject(value)
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
     * 从token中获取 payload 声明信息
     *
     * @param token token
     * @return payload 信息
     */
    public static String getUserName(String token) {
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTVerificationException e) {
            logger.warn("无效的token");
        }
        return null;
    }
}
