package com.video.live.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;

/**
 * 根据已知地图瓦片 x y z下载相应的瓦片地图
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/3 16:37
 */
public class DownloadTest {


    public static void main(String[] args) {
        getZxy();
        System.out.println("执行完毕");
    }

    public static String[] getZxy() {
        File baseFile = new File("D:\\BaiduNetdiskDownload\\高德离线地图-好用\\Leaflet.ChineseTmsProviders-master\\examples\\img");
        if (!FileUtil.exist(baseFile)) {
            return null;
        }
        File[] zFiles = baseFile.listFiles();
        for (File zFile : zFiles) {
            if (zFile.isDirectory()) {
                String z = zFile.getName();
                Integer integer = Integer.valueOf(z);

                File[] xFiles = zFile.listFiles();
                for (File xFile : xFiles) {
                    String x = xFile.getName();
                    File[] yFiles = xFile.listFiles();
                    for (File yFile : yFiles) {
                        String y = yFile.getName();
                        y = StrUtil.removeAll(y, ".png");
                        MapUtils.downloadImg(z, x, y);
                    }
                }
            }
        }
        return null;
    }
}
