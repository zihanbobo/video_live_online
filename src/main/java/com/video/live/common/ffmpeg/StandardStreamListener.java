package com.video.live.common.ffmpeg;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

/**
 * 标准流监听器
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:08
 */
@Slf4j
public class StandardStreamListener implements Runnable {

    private String videoURI;

    private String hlsFilePath;

    private boolean executeResult;

    private CountDownLatch countDownLatch;

    private BufferedReader reader;

    private String push_success_regex = ".*(frame|fps|Lsize|speed).*";

    @Override
    public void run() {
        try {
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                if (!executeResult) {
                    log.info("推流信息-->" + msg);
                }
                if (!executeResult && Pattern.matches(push_success_regex, msg) && FileUtil.exist(hlsFilePath)) {
                    this.executeResult = true;
                    countDown();
                }
            }
        } catch (Exception e) {

        } finally {
            IoUtil.close(reader);
            countDown();
            VideoStreamUtils.destroy(videoURI);
        }


    }

    public static StandardStreamListener build(String videoURI, String hlsFilePath, CountDownLatch countDownLatch, InputStream inputStream) {
        return new StandardStreamListener()
                .setVideoURI(videoURI)
                .setHlsFilePath(hlsFilePath)
                .setCountDownLatch(countDownLatch)
                .setReader(inputStream);
    }

    public String getVideoURI() {
        return videoURI;
    }

    public StandardStreamListener setVideoURI(String videoURI) {
        this.videoURI = videoURI;
        return this;
    }

    public String getHlsFilePath() {
        return hlsFilePath;
    }

    public StandardStreamListener setHlsFilePath(String hlsFilePath) {
        this.hlsFilePath = hlsFilePath;
        return this;
    }

    public boolean getExecuteResult() {
        return executeResult;
    }

    public StandardStreamListener setExecuteResult(boolean executeResult) {
        this.executeResult = executeResult;
        return this;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public StandardStreamListener setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        return this;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public StandardStreamListener setReader(InputStream reader) {
        this.reader = new BufferedReader(new InputStreamReader(reader));
        return this;
    }

    private void countDown() {
        if (Objects.nonNull(countDownLatch)) {
            countDownLatch.countDown();
        }
    }
}
