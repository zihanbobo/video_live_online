package com.video.live.common.ffmpeg;


/**
 * ffmpeg 推流任务信息
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:05
 */
public class VideoTaskInfo {

    /**
     * 任务 id
     */
    private String taskId;

    /**
     * 推流主进程
     */
    private Process process;

    /**
     * 标准流监听器
     */
    private StandardStreamListener standardListener;

    /**
     * 异常流监听器
     */
    private ErrorStreamListener errorListener;

    public static VideoTaskInfo build(String taskId, Process process, ErrorStreamListener errorStreamListener, StandardStreamListener standardListener) {
        return new VideoTaskInfo()
                .setErrorListener(errorStreamListener)
                .setProcess(process)
                .setTaskId(taskId)
                .setStandardListener(standardListener);
    }

    public String getTaskId() {
        return taskId;
    }

    public VideoTaskInfo setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public Process getProcess() {
        return process;
    }

    public VideoTaskInfo setProcess(Process process) {
        this.process = process;
        return this;
    }

    public StandardStreamListener getStandardListener() {
        return standardListener;
    }

    public VideoTaskInfo setStandardListener(StandardStreamListener standardListener) {
        this.standardListener = standardListener;
        return this;
    }

    public ErrorStreamListener getErrorListener() {
        return errorListener;
    }

    public VideoTaskInfo setErrorListener(ErrorStreamListener errorListener) {
        this.errorListener = errorListener;
        return this;
    }
}
