package com.video.live.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 17:46
 */
@Getter
@Setter
@ToString
@ApiModel(description = "输入的用户信息")
public class UserInputDTO {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(name = "性别")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(name = "性别")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @NotBlank(message = "电话不能为空")
    private String phone;

    @NotNull(message = "角色不能为空")
    private Long roleId;
}
