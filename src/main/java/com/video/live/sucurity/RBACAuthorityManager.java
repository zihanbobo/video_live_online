package com.video.live.sucurity;

import com.video.live.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//Resource Base Access Control 管理器
@Component("RBACAuthorityManager")
public class RBACAuthorityManager {

    Logger logger = LoggerFactory.getLogger(RBACAuthorityManager.class);

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean permit = false;
        if (principal instanceof SecurityUserDetails) {
            SecurityUserDetails userDetails = (SecurityUserDetails) principal;
            String requestURI = request.getRequestURI();
            List<Permission> permissions = userDetails.getPermissions();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            permit = permissions.stream()
                    .map(Permission::getAllowUri)
                    .anyMatch(url -> antPathMatcher.match(url, requestURI));
        }
        return permit;
    }
}
