package com.video.live.web.controller;

import com.video.live.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 13:56
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


}
