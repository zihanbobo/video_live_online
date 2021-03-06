package com.video.live.common.exception;

import com.video.live.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 14:40
 */
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseResult throwableHandler(Throwable e) {
        return ResponseResult.failed();
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        print(e);
        return ResponseResult.failed();
    }

    @ExceptionHandler(ServerException.class)
    public ResponseResult serverExceptionHandler(ServerException e) {
        print(e);
        return ResponseResult.failed(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(NoSuchResourceException.class)
    public ResponseResult noSuchExceptionHandler(NoSuchResourceException e) {
        print(e);
        return ResponseResult.failed(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler(OperationNotAllowException.class)
    public ResponseResult operationNotAllowException(OperationNotAllowException e) {
        print(e);
        return ResponseResult.failed(e.getErrorCode(), e.getErrorMsg());
    }

    private void print(Exception e) {
        logger.error("error msg--> {}", e);
    }
}
