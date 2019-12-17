package com.video.live.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 输入的视频信息
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:55
 */
@Getter
@Setter
@ApiModel(description = "视频输入信息")
public class VideoInputDTO {

    @ApiModelProperty(value = "视频地址", dataType = "string", required = true)
    @NotBlank(message = "视频地址不能为空")
    private String videoURI;

    @ApiModelProperty(value = "超时时间", dataType = "int", required = true)
    @Range(min = 15, message = "超时时间过段，最小15秒")
    private int timeOut;
}
