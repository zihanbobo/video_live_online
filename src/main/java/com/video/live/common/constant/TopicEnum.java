package com.video.live.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Deng Yunhu
 * @Date: 2020/3/26 17:37
 */
public enum TopicEnum {
    WEBSOCKET_CHANNEL_1_TOPIC,

    WEBSOCKET_CHANNEL_2_TOPIC;

    public static List<String> topicList = new ArrayList<>();

    static {
        TopicEnum[] values = values();
        for (TopicEnum topicEnum : values) {
            topicList.add(topicEnum.name());
        }
    }
}
