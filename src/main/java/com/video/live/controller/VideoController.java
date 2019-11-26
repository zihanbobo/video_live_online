package com.video.live.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频直播管理接口
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/25 17:28
 */
@Api(tags = "视频直播管理接口")
@RestController
public class VideoController {

    @ApiOperation(value = "播放视频")
    @PostMapping("/play")
    public Object videoPlay(String videoURI) {
        return true;
    }
}
