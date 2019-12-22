package com.video.live.sucurity.hanlder;

import com.video.live.common.response.ResponseEnum;
import com.video.live.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 16:34
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        logger.warn("登录失败");
        ResponseResult responseResult;
        if (exception instanceof UsernameNotFoundException) {
            responseResult = ResponseResult.failed(ResponseEnum.ACCOUNT_NOT_EXITS);
        } else if (exception instanceof BadCredentialsException) {
            responseResult = ResponseResult.failed(ResponseEnum.ACCOUNT_BAD);
        } else if (exception instanceof LockedException) {
            responseResult = ResponseResult.failed(ResponseEnum.ACCOUNT_LOCKED);
        } else {
            responseResult = ResponseResult.failed(ResponseEnum.ACCESS_DENIED);
        }
        ResponseResult.out(response, responseResult);
    }
}
