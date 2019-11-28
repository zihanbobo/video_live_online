package com.video.live.common.aspect;

import com.alibaba.fastjson.JSON;
import com.video.live.common.util.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:27
 */
@Aspect
@Component
public class LogAspect {

    private Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.video.live.controller.*.*(..))")
    public void log() {
    }


    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
        Method method = signature.getMethod();
        log.info("----------请求开始------------");
        log.info("请求IP:" + IpUtils.getIdAddr(request));
        log.info("请求URI:" + requestURI);
        log.info("请求方式:" + requestMethod);
        log.info("请求方式" + declaringTypeName.concat(".").concat(name));
        if (method.isAnnotationPresent(ApiOperation.class)) {
            String desc = method.getAnnotation(ApiOperation.class).value();
            log.info("方法描述:" + desc);
        }
        log.info("方法入参:" + Arrays.toString(args));

    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void afterReturing(Object object) {
        log.info("请求响应:" + JSON.toJSONString(object));
        log.info("----------请求结束----------");
    }
}
