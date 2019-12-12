package com.video.live.common.util;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/28 16:29
 */
public class IpUtils {


    private static final String IPV4_ADDR = "127.0.0.1";
    private static final String IPV6_ADDR = "0:0:0:0:0:0:0:1";
    private static final String X_FORWARDED = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final int IPV4_MAX_LENGTH = 16;

    public static String getIdAddr(HttpServletRequest request) {
        String ipAddr = request.getHeader(X_FORWARDED);
        if (NetUtil.isUnknow(ipAddr)) {
            ipAddr = request.getHeader(PROXY_CLIENT_IP);
        }
        if (NetUtil.isUnknow(ipAddr)) {
            ipAddr = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (NetUtil.isUnknow(ipAddr)) {
            ipAddr = request.getRemoteAddr();
            if (StrUtil.equalsAny(ipAddr, IPV4_ADDR, IPV6_ADDR)) {
                ipAddr = NetUtil.getLocalhostStr();
            }
        }
        if (!NetUtil.isUnknow(ipAddr) && ipAddr.length() > IPV4_MAX_LENGTH) {
            ipAddr = NetUtil.getMultistageReverseProxyIp(ipAddr);
        }
        return ipAddr;
    }
}
