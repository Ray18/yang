package com.xi.xlm.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.len.util.HttpUtil;
import com.len.util.JsonUtils;
import com.xi.xlm.wx.entity.WeiXinMiniLive;
import com.xi.xlm.wx.entity.WinXinMiniLiveReplay;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: WeiXinUtil
 * @author:by yangtianfeng
 * @classDate: 2020/9/3 12:01
 * @Version: 1.0
 **/

public class WeiXinUtil {

    private static final String getAccessToken = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String getPaidUnionId = "https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID";
    private static final String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session";

    private static final String getLive = "https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";

    private static final String GET_REPLAY = "https://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";


    public static String getAccessToken(String appid, String secret) {
        Map<String, String> params = new HashMap<>();
        params.put("appid", appid);
        params.put("secret", secret);
        params.put("grant_type", "client_credential");
        HttpUtil httpUtil = HttpUtil.init();
        httpUtil.setParamMap(params);
        Map<String, String> post = httpUtil.get(getAccessToken);
        JSONObject resultObj = JSONObject.parseObject(post.get("result"));
        if (StringUtils.hasText(resultObj.getString("access_token"))) {
            return resultObj.getString("access_token");
        }

        return null;
    }


    public static String code2Session(String appid, String appSecret, String jsCode) {

        Map<String, String> params = new HashMap<>();
        params.put("appid", appid);
        params.put("secret", appSecret);
        params.put("js_code", jsCode);
        params.put("grant_type", "authorization_code");
        HttpUtil httpUtil = HttpUtil.init();
        httpUtil.setParamMap(params);
        Map<String, String> post = httpUtil.get(code2SessionUrl);
        return JsonUtils.map2Json(post);
    }


    public static WeiXinMiniLive getLiveInfo(String token, Number start, Number limit) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", start);
        jsonObject.put("limit", limit);
        String sendInfo = cn.hutool.http.HttpUtil.post(getLive + token, jsonObject.toJSONString());
        WeiXinMiniLive lives = JsonUtils.json2Bean(sendInfo, WeiXinMiniLive.class);

        return lives;

    }


    public static WinXinMiniLiveReplay getLiveInfoReplay(String token, String room_id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("start", 0);
        jsonObject.put("limit", 100);
        jsonObject.put("action", "get_replay");
        jsonObject.put("room_id", room_id);
        String sendInfo = cn.hutool.http.HttpUtil.post(GET_REPLAY + token, jsonObject.toJSONString());
        WinXinMiniLiveReplay lives = JsonUtils.json2Bean(sendInfo, WinXinMiniLiveReplay.class);

        return lives;

    }
}
