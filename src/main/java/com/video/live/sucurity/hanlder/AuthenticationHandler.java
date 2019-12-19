package com.video.live.sucurity.hanlder;

import com.video.live.common.response.ResponseEnum;
import com.video.live.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理器
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/19 15:12
 */
@Component
public class AuthenticationHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.warn("权限不足，不允许访问");
        ResponseResult.out(response, ResponseResult.failed(ResponseEnum.ACCESS_DENIED));
    }
}
