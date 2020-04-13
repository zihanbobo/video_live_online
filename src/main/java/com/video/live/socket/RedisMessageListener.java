package com.video.live.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Redis主题监听器
 *
 * @Author: Deng Yunhu
 * @Date: 2020/3/26 17:52
 */
@Configuration
public class RedisMessageListener implements MessageListener {

    /**
     * 接收主题消息
     *
     * @param message 接收到的消息
     * @param pattern 订阅的主题
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic=new String(pattern);
        String body=new String(message.getBody());
        System.out.println("订阅的主题是:"+topic);
        System.out.println("接收到的消息:"+body);
    }
}
