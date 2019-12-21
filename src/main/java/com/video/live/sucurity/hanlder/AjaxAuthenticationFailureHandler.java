package com.video.live.sucurity.hanlder;

import com.video.live.common.response.ResponseEnum;
import com.video.live.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 16:34
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.warn("权限不足，不允许访问"+exception);
        ResponseResult.out(response, ResponseResult.failed(ResponseEnum.ACCESS_DENIED));
    }
}
