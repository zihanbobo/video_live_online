package com.video.live.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;

/**
 * 实体转换工具
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:07
 */
public class EntityConvert {

    public static <T, S> T convert(S source, Class<T> targetClazz) {
        T t=ReflectUtil.newInstance(targetClazz);
        BeanUtil.copyProperties(source,t);
        return t;
    }

    public static <T, S> T convert(S source, Class<T> targetClazz, String... ignoreProperties) {
        T t = ReflectUtil.newInstance(targetClazz);
        BeanUtil.copyProperties(source, t, ignoreProperties);
        return t;
    }
}
