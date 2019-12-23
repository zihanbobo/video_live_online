package com.video.live.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/23 17:09
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "security.ignore")
@Component
public class SecurityProperties {

    private List<String> uris;
}
