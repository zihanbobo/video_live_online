package com.video.live.sucurity;

import com.video.live.common.constant.EntityConstant;
import com.video.live.common.util.EntityConvert;
import com.video.live.entity.User;
import com.video.live.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 11:11
 */
@Component
public class UserDetailsServerImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username).orElse(null);
        if (Objects.isNull(user)) {
            user = new User();
            user.setUserName(EntityConstant.ANY_ANONYMOUS_USER);
        }
        SecurityUserDetails convert = EntityConvert.convert(user, SecurityUserDetails.class);
        return convert;
    }
}
