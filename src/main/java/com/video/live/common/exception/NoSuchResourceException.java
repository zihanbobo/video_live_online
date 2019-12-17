package com.video.live.common.exception;

import com.video.live.common.base.BaseException;
import com.video.live.common.response.ResponseEnum;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:42
 */
public class NoSuchResourceException extends BaseException {

    public NoSuchResourceException() {
        super();
        ResponseEnum noSuchException = ResponseEnum.NO_SUCH_EXCEPTION;
        setErrorCode(noSuchException.getCode());
        setErrorMsg(noSuchException.getDesc());
    }

    public NoSuchResourceException(String errorMsg) {
        ResponseEnum noSuchException = ResponseEnum.NO_SUCH_EXCEPTION;
        setErrorMsg(errorMsg);
        setErrorCode(noSuchException.getCode());
    }

}
