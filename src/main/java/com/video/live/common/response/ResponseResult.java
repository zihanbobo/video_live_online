package com.video.live.common.response;

import com.video.live.common.exception.ServerException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * rest请求响应类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 11:08
 */
@Getter
@Setter
@ToString
@SuppressWarnings("unchecked")
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String desc;
    private T data;
    private Date time = new Date();

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T t) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseEnum.SUCCESS.getCode());
        responseResult.setDesc(ResponseEnum.SUCCESS.getDesc());
        responseResult.setData(t);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed(T t) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseEnum.FAILED.getCode());
        responseResult.setDesc(ResponseEnum.FAILED.getDesc());
        responseResult.setData(t);
        return responseResult;
    }

    public static ResponseResult<ResponseEnum> failed(ResponseEnum responseEnum){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(responseEnum.getCode());
        responseResult.setDesc(responseEnum.getDesc());
        responseResult.setData(null);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed() {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResponseEnum.FAILED.getCode());
        responseResult.setDesc(ResponseEnum.FAILED.getDesc());
        responseResult.setData(null);
        return responseResult;
    }

    public static <T> ResponseResult<T> failed(Integer code, String desc) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setDesc(desc);
        return responseResult;
    }

    public static <T> void out(HttpServletResponse response, T message) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter writer = response.getWriter()) {
            writer.print(message);
            writer.flush();
        } catch (Exception e) {
            throw new ServerException("消息输出失败");
        }
    }
}
