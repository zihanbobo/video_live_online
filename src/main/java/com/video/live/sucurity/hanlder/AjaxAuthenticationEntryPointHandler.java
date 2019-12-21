package com.video.live.sucurity.hanlder;

import com.video.live.common.response.ResponseEnum;
import com.video.live.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 16:31
 */
@Component
public class AjaxAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AjaxAuthenticationEntryPointHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.warn("权限不足，不允许访问");
        //ResponseResult.out(response, ResponseResult.failed(ResponseEnum.ACCESS_DENIED));
    }
}
