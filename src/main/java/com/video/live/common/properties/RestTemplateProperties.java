package com.video.live.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * restTemplate 参数配置类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/6 17:41
 */
@Configuration
@PropertySource(value = {"classpath:/config/restTemplate.properties"})
@Getter
@Setter
@ToString
public class RestTemplateProperties {

    @Value("${read.timeOut}")
    private int readTimeOut;

    @Value("${write.timeOut}")
    private int writeTimeOut;

    @Value("${connection.timeOut}")
    private int connectTimeOut;
}
