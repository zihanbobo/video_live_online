package com.video.live.common.exception;

import com.video.live.common.base.BaseException;
import com.video.live.common.response.ResponseEnum;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:55
 */
public class OperationNotAllowException extends BaseException {

    public OperationNotAllowException() {
        super(ResponseEnum.OPERATION_NOT_ALLOW);
    }

    public OperationNotAllowException(String errorMsg) {
        super(ResponseEnum.OPERATION_NOT_ALLOW.getCode(), errorMsg);
    }
}
