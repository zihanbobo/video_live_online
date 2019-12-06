package com.video.live.common.response;


/**
 * rest响应枚举类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:17
 */
public enum ResponseEnum {

    SUCCESS("SUCCESS", 200),

    FAILED("FAILED", 500);

    private Integer code;

    private String desc;

    ResponseEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
