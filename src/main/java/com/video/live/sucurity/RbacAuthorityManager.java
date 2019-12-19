package com.video.live.sucurity;

import com.video.live.entity.Permission;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("rbacAuthorityManager")
public class RbacAuthorityManager {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof SecurityUserDetails) {
            SecurityUserDetails userDetails = (SecurityUserDetails) principal;
            String requestURI = request.getRequestURI();
            List<Permission> permissions = userDetails.getPermissions();
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (Permission permission : permissions) {
                boolean match = antPathMatcher.match(permission.getPermissionName(), requestURI);
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
