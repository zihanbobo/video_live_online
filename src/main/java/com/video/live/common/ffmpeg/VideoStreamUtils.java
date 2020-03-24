package com.video.live.common.ffmpeg;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import com.video.live.common.exception.ServerException;
import com.video.live.common.thread.ThreadPoolUtil;
import com.video.live.common.util.PathUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/25 16:52
 */
public class VideoStreamUtils {

    /**
     * ffmpeg 将rtsp流推送为 HLS (Http Live Streaming)
     */
    private static final String FFMPEG_PUSH_RTSP_CMD = "ffmpeg -rtsp_transport tcp -i {} -fflags flush_packets -max_delay 10 -flags -global_header -hls_time 10 -hls_list_size 10 -hls_wrap 10 -vcodec copy -y {}";

    /**
     * ffmpeg 将rtmp流推送为 HLS (Http Live Streaming)
     */
    private static final String FFMPEG_PUSH_RTMP_CMD = "ffmpeg -re -i {} -fflags flush_packets -max_delay 10 -flags -global_header -hls_time 10 -hls_list_size 10 -hls_wrap 10 -vcodec copy -y {}";

    /**
     * HLS (Http Live Streaming) 流访问路径
     */
    private static final String HLS_BASE_URI = "/live/{}.m3u8";
    private static final String RTSP_PATTERN = "rtsp";
    private static final int MIN_TIME_OUT = 15;
    private static final int ONE_THREAD = 1;

    private static final ConcurrentHashMap<String, VideoTaskInfo> STREAM_MAP = new ConcurrentHashMap();

    public static synchronized String play(String videoURI, int timeOut) {
        VideoTaskInfo taskInfo = getTaskInfo(videoURI);
        if (Objects.nonNull(taskInfo)) {
            timeOut = Math.max(MIN_TIME_OUT, timeOut);
            taskInfo = execute(videoURI, timeOut);
            STREAM_MAP.put(videoURI, taskInfo);
        }
        return taskInfo.getTaskId();
    }

    private static VideoTaskInfo execute(String videoURI, int timeOut) {
        Process process = null;
        ErrorStreamListener errorListener = null;
        StandardStreamListener standardListener = null;
        try {
            String taskId = IdUtil.fastSimpleUUID();
            String command = buildCommand(videoURI, taskId);
            process = RuntimeUtil.exec(command);
            CountDownLatch countDownLatch = new CountDownLatch(ONE_THREAD);
            errorListener = ErrorStreamListener.build(videoURI, process.getErrorStream());
            standardListener = StandardStreamListener.build(videoURI, getHLSPath(taskId), countDownLatch, process.getInputStream());
            ThreadPoolUtil.execute(standardListener);
            ThreadPoolUtil.execute(errorListener);
            boolean await = countDownLatch.await(timeOut, TimeUnit.SECONDS);
            if (!await){
                destroy(process, errorListener, standardListener);
                throw new ServerException("视频流推送失败");
            }
            if (!standardListener.getExecuteResult()) {
                destroy(process, errorListener, standardListener);
                throw new ServerException("视频流推送失败");
            }
            return VideoTaskInfo.build(taskId, process, errorListener, standardListener);

        } catch (Exception e) {
            destroy(process, errorListener, standardListener);
            throw new ServerException("视频流推送失败");
        }
    }

    public static void destroy(String videoURI) {
        VideoTaskInfo taskInfo = getTaskInfo(videoURI);
        if (Objects.nonNull(taskInfo)) {
            destroy(taskInfo.getProcess(), taskInfo.getErrorListener(), taskInfo.getStandardListener());
        }
    }

    private static void destroy(Process process, Runnable... runnables) {
        RuntimeUtil.destroy(process);
        for (Runnable runnable : runnables) {
            if (Objects.nonNull(runnable)) {
                ThreadPoolUtil.remove(runnable);
            }
        }
    }

    private static VideoTaskInfo getTaskInfo(String videoURI) {
        return STREAM_MAP.get(videoURI);
    }

    private static String getHLSPath(String taskId) {
        return StrBuilder.create(PathUtils.getApplicationJarHome())
                .append(taskId)
                .append(HLS_BASE_URI)
                .toString();
    }

    private static String buildCommand(String videoURI, String taskId) {
        String baseCommand;
        if (videoURI.startsWith(RTSP_PATTERN)) {
            baseCommand = FFMPEG_PUSH_RTSP_CMD;
        } else {
            baseCommand = FFMPEG_PUSH_RTMP_CMD;
        }

        return StrUtil.format(baseCommand, videoURI, taskId);
    }

    public static String buildHLSURI(String taskId) {
        return StrUtil.format(HLS_BASE_URI, taskId);
    }
}
