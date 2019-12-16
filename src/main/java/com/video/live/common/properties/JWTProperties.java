package com.video.live.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/16 16:04
 */
@PropertySource({"classpath:/config/jwt.properties"})
@Getter
@Setter
@ToString
@Component
public class JWTProperties {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expires.time}")
    private Long ExpiresTime;
}
