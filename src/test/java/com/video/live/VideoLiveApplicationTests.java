package com.video.live;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootTest
class VideoLiveApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String pictureURL = "";
        String storagePath="D:\\test.png";
        try (OutputStream outputStream=new BufferedOutputStream(new FileOutputStream(new File(storagePath)))){
            ResponseEntity<byte[]> exchange = restTemplate.exchange(pictureURL, HttpMethod.GET, new HttpEntity<>(), byte[].class);
           // IoUtil.copy(exchange.getBody(),outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
