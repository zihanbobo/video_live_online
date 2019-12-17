package com.video.live.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:42
 */
@Getter
@Setter
@ToString
public class BaseException extends RuntimeException implements Serializable {

    private Integer errorCode;

    private String errorMsg;

    public BaseException(){
        super();
    }

    public BaseException(Integer errorCode,String errorMsg){
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
}
