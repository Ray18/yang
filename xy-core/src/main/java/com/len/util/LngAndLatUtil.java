package com.len.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.util.StringUtils;

/**
 * @className: LngAndLatUtil
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 21:11
 * @Version: 1.0
 **/
public class LngAndLatUtil {



    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 经度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;

        // 维度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;

        double R = 6371;

        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d * 1000;
    }

}
