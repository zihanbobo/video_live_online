package com.video.live.config;

import com.google.common.collect.Lists;
import com.video.live.common.properties.RestTemplateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * RestTemplate配置类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/6 17:22
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateProperties properties;

    @Bean
    public RestTemplate restTemplate(OkHttp3ClientHttpRequestFactory httpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        //restTemplate.setMessageConverters(getConverter());
        return restTemplate;
    }

    /**
     * 初始化http请求工厂，restTemplate的具体请求实现有多种 如 SimpleClientHttpRequestFactory 、OkHttp3ClientHttpRequestFactory
     * @return http请求工厂
     */
    @Bean
    public OkHttp3ClientHttpRequestFactory restTemplateFactory() {
        OkHttp3ClientHttpRequestFactory httpRequestFactory = new OkHttp3ClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        httpRequestFactory.setWriteTimeout(5000);
        return httpRequestFactory;
    }

    /**
     * 设置数据转换器
     * @return 转换器集合
     */
    private List<HttpMessageConverter<?>> getConverter() {
        List<MediaType> supportMediaTypes = Lists.newArrayList(MediaType.TEXT_HTML,MediaType.TEXT_PLAIN);
        StringHttpMessageConverter httpMessageConverter = new StringHttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(supportMediaTypes);
        return Lists.newArrayList(httpMessageConverter);
    }
}
