package com.video.live.config;

import com.video.live.common.core.SpringSecurityHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2020/1/3 16:45
 */
@Configuration
public class UserAuditorConfig implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SpringSecurityHolder.currentUserName());
    }
}
