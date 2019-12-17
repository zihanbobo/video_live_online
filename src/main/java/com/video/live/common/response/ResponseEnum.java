package com.video.live.common.response;


/**
 * rest响应枚举类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:17
 */
public enum ResponseEnum {

    SUCCESS("SUCCESS", 2000),

    FAILED("FAILED", 5000),

    OPERATION_NOT_ALLOW("操作不允许",4003),

    NO_SUCH_EXCEPTION("资源不存在",4004);



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
