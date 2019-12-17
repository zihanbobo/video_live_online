package com.video.live.web.controller;

import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.ValidationUtils;
import com.video.live.model.input.PermissionInputDTO;
import com.video.live.model.input.RoleInputDTO;
import com.video.live.web.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:24
 */
@RestController
@Api(tags = "角色权限管理接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("添加权限")
    @PostMapping(value = "/permission")
    public ResponseResult addPermission(@Validated @RequestBody PermissionInputDTO inputDTO, BindingResult bindingResult) {
        ValidationUtils.checkBindingResult(bindingResult);
        permissionService.addPermission(inputDTO);
        return ResponseResult.success(null);
    }


    @ApiOperation("添加角色和权限")
    @PostMapping(value = "/permission/role")
    public ResponseResult addRole(@Validated @RequestBody RoleInputDTO inputDTO, BindingResult bindingResult) {
        ValidationUtils.checkBindingResult(bindingResult);
        permissionService.addRole(inputDTO);
        return ResponseResult.success(null);
    }

}
