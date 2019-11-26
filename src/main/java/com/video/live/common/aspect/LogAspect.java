package com.video.live.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:27
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.video.live.controller.*(*.*))")
    public void log() {
    }


    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String remoteHost = request.getRemoteHost();

        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求IP:" + remoteHost);
        log.info("请求URI:" + requestURI);
        log.info("请求方式:" + method);
        log.info("请求方式" + declaringTypeName.concat(".").concat(name));
        log.info("方法入参:" + Arrays.toString(args));
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturing(Object object) {
        log.info("请求响应:" + object);
    }
}
