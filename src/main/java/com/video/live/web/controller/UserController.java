package com.video.live.web.controller;

import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.ValidationUtils;
import com.video.live.model.input.UserInputDTO;
import com.video.live.web.service.UserService;
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
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseResult addUser(@RequestBody @Validated UserInputDTO inputDTO, BindingResult bindingResult) {
        ValidationUtils.checkBindingResult(bindingResult);
        return ResponseResult.success(null);
    }
}
