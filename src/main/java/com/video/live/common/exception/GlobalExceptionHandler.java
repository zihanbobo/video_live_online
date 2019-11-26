package com.video.live.common.exception;

import com.video.live.common.response.ResponseResult;
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


    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        return ResponseResult.failed();
    }

    @ExceptionHandler(ServerException.class)
    public ResponseResult serverExceptionHandler(ServerException e) {
        return ResponseResult.failed(e.getErrorCode(), e.getErrorMsg());
    }
}
