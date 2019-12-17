package com.video.live.common.exception;

import com.video.live.common.base.BaseException;
import com.video.live.common.response.ResponseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义服务异常类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 14:42
 */
@Getter
@Setter
public class ServerException extends BaseException {

    public ServerException() {
        super();
        setErrorCode(ResponseEnum.FAILED.getCode());
        setErrorMsg(ResponseEnum.FAILED.getDesc());
    }

    public ServerException(String errorMsg) {
        setErrorMsg(errorMsg);
        setErrorCode(ResponseEnum.FAILED.getCode());
    }
}
