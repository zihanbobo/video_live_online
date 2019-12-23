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

    NO_SUCH_EXCEPTION("资源不存在",4004),

     ACCESS_DENIED("权限不足",4005),

    ACCOUNT_LOCKED("账号被锁定",4006),

    ACCOUNT_NOT_EXITS("账号不存在",4007),

    ACCOUNT_BAD("用户名或密码错误",4008);






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
