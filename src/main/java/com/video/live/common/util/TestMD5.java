package com.video.live.common.util;

import cn.hutool.crypto.digest.DigestUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/5 17:19
 */
public class TestMD5 {
    public static void main(String[] args){
        String  basePath="D:\\BaiduNetdiskDownload\\高德离线地图-好用\\Leaflet.ChineseTmsProviders-master\\examples\\img\\";
        ArrayList<String> strings = Lists.newArrayList(basePath+"4\\2\\0.png", basePath+"4\\3\\0.png");
        for (String path:strings){
            String bytes = DigestUtil.md5Hex(path);
            System.out.println(bytes);
        }
    }
}
