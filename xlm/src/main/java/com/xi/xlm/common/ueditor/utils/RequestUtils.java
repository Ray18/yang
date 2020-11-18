//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xi.xlm.common.ueditor.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class RequestUtils {
    public static final String REQUEST_AJAX_HEADER = "X-Requested-With";
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static String RealPath = null;
    private static String WebappsDir = null;
    private static String RequestServer = null;

    /**
     * 自定义文件路径
     */
    @Value("${lenosp.localDomain}")
    private String uploadPath;
    private static String uploadPathStatic;
    @PostConstruct
    public void getApiToken() {
        uploadPathStatic = this.uploadPath;
    }

    public static String getUploadPath() {
        // lockie.zou
        return uploadPathStatic;
    }

    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception var1) {
            return null;
        }
    }

    public static String getBasePath(HttpServletRequest request) {
        if (RequestServer == null) {
            String port = request.getServerPort() + "";
            port = "80".equals(port) ? "" : ":" + port;
            String requestServer = request.getScheme() + "://" + request.getServerName() + port;
            RequestServer = requestServer ;
        }

        String uploadPath = getUploadPath();
        if(StringUtils.isNotBlank(uploadPath)){
            RequestServer = uploadPath;
        }
        return RequestServer + request.getContextPath();
    }

    public static String getRealPath(HttpServletRequest request) {
        if (RealPath == null) {
            String currAppDir = request.getRealPath("/").replace("\\", "/");
            RealPath = currAppDir.substring(0, currAppDir.length() - 1);
        }

        return RealPath;
    }

    public static String getWebappsDir(HttpServletRequest request) {
        if (WebappsDir == null) {
            String currAppDir = request.getRealPath("/").replace("\\", "/");
            currAppDir = currAppDir.substring(0, currAppDir.length() - 1);
            WebappsDir = currAppDir.substring(0, currAppDir.lastIndexOf("/"));
        }

        return WebappsDir;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException var3) {
                ip = "127.0.0.1";
            }
        }

        if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        return ip;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest");
    }
}
