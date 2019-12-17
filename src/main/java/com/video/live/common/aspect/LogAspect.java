package com.video.live.common.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.video.live.common.util.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 日志切面
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:27
 */
@Aspect
@Component
@Order(1)
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Stopwatch> stopWatchThreadLocal = new ThreadLocal<>();

    @Pointcut("within(com.video.live.web.controller.*)&&(" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping)||" +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping))")
    public void webLog() {
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        stopWatchThreadLocal.set(Stopwatch.createStarted());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String name = signature.getName();
        Object[] args = joinPoint.getArgs();
        Method method = signature.getMethod();
        logger.info("----------请求开始------------");
        logger.info("请求IP:{}", IpUtils.getIdAddr(request));
        logger.info("请求URI:{}", requestURI);
        logger.info("请求方式:{}", requestMethod);
        logger.info("请求方法:{}", declaringTypeName.concat(".").concat(name));
        if (method.isAnnotationPresent(ApiOperation.class)) {
            String desc = method.getAnnotation(ApiOperation.class).value();
            logger.info("方法描述:{}", desc);
        }
        logger.info("方法入参:{}", Arrays.toString(args));
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void afterReturning(Object object) {
        logger.info("请求响应:{}", JSON.toJSONString(object));
        printAfterReturning();
    }

    @AfterThrowing(pointcut = "webLog()")
    public void afterThrowing(JoinPoint joinPoint) {
        logger.info("接口执行异常,异常描述");
        printAfterReturning();
    }

    private void printAfterReturning() {
        Stopwatch stopwatch = stopWatchThreadLocal.get();
        stopwatch.stop();
        logger.info("请求耗时 {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        logger.info("----------请求结束------------");
    }
}
