package com.video.live.common.exception;

import com.video.live.common.base.BaseException;
import com.video.live.common.response.ResponseEnum;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:42
 */
public class NoSuchResourceException extends BaseException {

    public NoSuchResourceException() {
        super(ResponseEnum.NO_SUCH_EXCEPTION);
    }

    public NoSuchResourceException(String errorMsg) {
        super(ResponseEnum.NO_SUCH_EXCEPTION.getCode(), errorMsg);
    }

}
