package com.video.live.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:07
 */
@Getter
@Setter
@ToString
@ApiModel(description = "角色输入信息")
public class RoleInputDTO {


    @ApiModelProperty("角色名称")
    @NotBlank(message = "名称不能为空")
    private String roleName;

    @NotEmpty(message = "权限不能为空")
    private List<Long> permissionId;
}
