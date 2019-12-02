package com.video.live.common.ffmpeg;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 16:08
 */
@Slf4j
public class ErrorStreamListener implements Runnable {

    private String videoURI;

    private BufferedReader reader;

    @Override
    public void run() {
        try {
            String msg=null;
            while ((msg=reader.readLine())!=null){
                log.error("推流异常消息-->"+msg);
            }
        }catch (Exception e){

        }finally {
            IoUtil.close(reader);
        }

    }

    public static ErrorStreamListener build(String videoURI, InputStream inputStream){
        return new ErrorStreamListener()
                .setVideoURI(videoURI)
                .setReader(inputStream);
    }

    public String getVideoURI() {
        return videoURI;
    }

    public ErrorStreamListener setVideoURI(String videoURI) {
        this.videoURI = videoURI;
        return this;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public ErrorStreamListener setReader(InputStream reader) {
        this.reader = new BufferedReader(new InputStreamReader(reader));
        return this;
    }
}
