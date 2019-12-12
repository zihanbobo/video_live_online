package com.video.live.controller;

import com.video.live.common.response.ResponseResult;
import com.video.live.common.util.ValidationUtils;
import com.video.live.model.VideoInputDTO;
import com.video.live.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 视频直播管理接口
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/25 17:28
 */
@Api(tags = "视频直播管理接口")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "播放视频")
    @PostMapping("/video/play")
    public ResponseResult<String> videoPlay(@RequestBody @Valid VideoInputDTO inputDTO, BindingResult bindResult) {
        ValidationUtils.checkBindingResult(bindResult);
        String hlsURI = videoService.play(inputDTO.getVideoURI(), inputDTO.getTimeOut());
        return ResponseResult.success(hlsURI);
    }

    @ApiOperation(value = "停止视频播放")
    @GetMapping("/video/stop")
    public ResponseResult<Boolean> videoStop(String videoURI) {
        ValidationUtils.checkIsNull(videoURI);
        videoService.stop(videoURI);
        return ResponseResult.success(true);
    }
}
