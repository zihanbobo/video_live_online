package com.video.live.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:17
 */
@Getter
@Setter
@ToString
@ApiModel(description = "输入的权限信息")
public class PermissionInputDTO {

    @ApiModelProperty(value = "权限名称")
    @NotNull(message = "权限名称不能为空")
    private String permissionName;

    @NotNull(message = "可访问uri不能为空")
    private String allowUri;
}
