package com.video.live.web.controller;

import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.ValidationUtils;
import com.video.live.model.input.UserInputDTO;
import com.video.live.web.service.UserService;
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
 * @Date: 2019/12/17 13:56
 */
@Api(tags = "用户信息管理接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("添加用户和角色")
    @PostMapping(value = "/user")
    public ResponseResult addUser(@RequestBody @Validated UserInputDTO inputDTO, BindingResult bindingResult) {
        ValidationUtils.checkBindingResult(bindingResult);
        userService.addUser(inputDTO);
        return ResponseResult.success(null);
    }
}
