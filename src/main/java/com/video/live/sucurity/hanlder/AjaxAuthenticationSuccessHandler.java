package com.video.live.sucurity.hanlder;

import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.JWTUtils;
import com.video.live.sucurity.SecurityUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.video.live.common.constant.EntityConstant.TOKEN_BEARER;

/**
 * 登录成功处理器
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 16:36
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUserDetails details =(SecurityUserDetails) authentication.getPrincipal();
        String token = JWTUtils.generate(details.getUsername());
        ResponseResult.out(response,ResponseResult.success(TOKEN_BEARER.concat(token)));
    }
}
