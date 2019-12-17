package com.video.live.common.exception;

import java.util.function.Supplier;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:50
 */
public class ExceptionSupport {

    public static Supplier<NoSuchResourceException> noSuchResourceExceptionSupplier(String errorMsg) {
        return () -> new NoSuchResourceException(errorMsg);
    }

    public static Supplier<OperationNotAllowException> operationNotAllowExceptionSupplier(String errorMsg) {
        return () -> new OperationNotAllowException(errorMsg);
    }

    public static Supplier<ServerException> serverExceptionSupplier(String errorMsg) {
        return () -> new ServerException(errorMsg);
    }
}
