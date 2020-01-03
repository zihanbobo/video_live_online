package com.video.live.common.core;

import com.video.live.common.constant.EntityConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2020/1/3 16:45
 */
public class SpringSecurityHolder {

    public static String currentUserName() {
        return Optional.ofNullable(getAuthentication())
                .map(Authentication::getName)
                .orElse(EntityConstant.ANY_ANONYMOUS_USER);

    }

    public static Authentication getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .orElse(null);
    }
}
