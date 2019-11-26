package com.video.live.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Swagger2  配置信息
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/25 17:17
 */
@Getter
@Setter
@Configuration
@PropertySource(value = {"classpath:/config/swagger2.properties"})
public class SwaggerProperties {

    @Value("${swagger2.basePackage}")
    private String basePackage;

    @Value("${swagger2.tile}")
    private String tile;

    @Value("${swagger2.description}")
    private String description;

    @Value("${swagger2.version}")
    private String version;

    @Value("${swagger2.termOfServiceUrl}")
    private String termOfServiceUrl;

    @Value("${swagger2.concatName}")
    private String concatName;

    @Value("${swagger2.contactUrl}")
    private String contactUrl;

    @Value("${swagger2.swagger2Email}")
    private String email;
}
