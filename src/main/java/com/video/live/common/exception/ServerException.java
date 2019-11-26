package com.video.live.common.exception;

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
public class ServerException extends RuntimeException {

    private Integer errorCode;

    private String errorMsg;

    public ServerException() {
        super();
    }

    public ServerException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ServerException(String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = ResponseEnum.FAILED.getCode();
    }
}
