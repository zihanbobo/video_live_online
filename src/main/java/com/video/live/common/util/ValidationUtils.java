package com.video.live.common.util;

import com.video.live.common.exception.ServerException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:59
 */
public class ValidationUtils {

    public static void checkBindingResult(BindingResult bindResult) {
        List<ObjectError> allErrors = bindResult.getAllErrors();
        for (ObjectError error : allErrors) {
            String defaultMessage = error.getDefaultMessage();
            throw new ServerException(defaultMessage);
        }
    }

    public static void checkIsNull(Object... args) {
        for (Object object : args) {
            if (Objects.isNull(object)) {
                throw new ServerException("请求参数不能为空");
            }
        }
    }
}
