package com.video.live.service;

/**
 * 视频服务类接口
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:50
 */
public interface VideoService {

    /**
     * 推送视频流为 HLS (Http Live Streaming)
     *
     * @param videoURI
     * @param timeOut
     * @return
     */
    String play(String videoURI, int timeOut);

    /**
     * 停止视频流的推送
     *
     * @param videoURI
     */
    void stop(String videoURI);
}
