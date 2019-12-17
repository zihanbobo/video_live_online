package com.video.live.web.service.impl;

import com.video.live.common.ffmpeg.VideoStreamUtils;
import com.video.live.web.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * 视频服务实现类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:52
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String play(String videoURI, int timeOut) {
        String taskId = VideoStreamUtils.play(videoURI, timeOut);
        return VideoStreamUtils.buildHLSURI(taskId);
    }

    @Override
    public void stop(String videoURI) {
        VideoStreamUtils.destroy(videoURI);
    }
}
