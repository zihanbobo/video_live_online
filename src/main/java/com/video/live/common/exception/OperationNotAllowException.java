package com.video.live.common.exception;

import com.video.live.common.base.BaseException;
import com.video.live.common.response.ResponseEnum;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:55
 */
public class OperationNotAllowException extends BaseException {

    public OperationNotAllowException() {
        super();
        setErrorCode(ResponseEnum.OPERATION_NOT_ALLOW.getCode());
        setErrorMsg(ResponseEnum.OPERATION_NOT_ALLOW.getDesc());
    }

    public OperationNotAllowException(String errorMsg) {
        setErrorCode(ResponseEnum.OPERATION_NOT_ALLOW.getCode());
        setErrorMsg(errorMsg);
    }
}
