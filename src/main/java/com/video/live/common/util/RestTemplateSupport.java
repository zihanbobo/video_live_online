package com.video.live.common.util;

import com.google.common.collect.Lists;
import com.video.live.common.exception.ServerException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/9 14:48
 */
@SuppressWarnings("unchecked")
public class RestTemplateSupport {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void download(String url, String storagePath) {
        try {
            List<MediaType> mediaTypes = Lists.newArrayList(MediaType.APPLICATION_OCTET_STREAM);
            HttpEntity<byte[]> httpEntity = new HttpEntity(mediaTypes);
            ResponseEntity<byte[]> exchange = getRestTemplate().exchange(url, HttpMethod.GET, httpEntity, byte[].class);
            HttpStatus httpStatus = exchange.getStatusCode();
            if (httpStatus.is2xxSuccessful()) {
                FileCopyUtils.copy(Objects.requireNonNull(exchange.getBody()), new File(storagePath));
            }
        } catch (IOException e) {
            throw new ServerException("文件下载失败");
        }
    }

    private static RestTemplate getRestTemplate() {
        if (Objects.isNull(restTemplate)) {
            return new RestTemplate();
        }
        return restTemplate;
    }
}
