package com.xi.xlm.wx.service._impl;

import com.alibaba.fastjson.JSONObject;
import com.len.redis.RedisUtil;
import com.len.util.HttpUtil;
import com.len.util.JsonUtils;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.exception.UnAuthorizedException;
import com.xi.xlm.request.w.MiNiLoginReq;
import com.xi.xlm.service.IWebMemberService;
import com.xi.xlm.wx.entity.BaseToken;
import com.xi.xlm.wx.entity.WeiXinUserInfo;
import com.xi.xlm.wx.entity.WxAccount;
import com.xi.xlm.wx.service.WxAppletService;
import com.xi.xlm.wx.util.AesCbcUtil;
import com.xi.xlm.wx.util.JwtWxConfig;
import com.xi.xlm.wx.util.WeiXinUtil;
import com.xi.xlm.wx.vo.Code2SessionResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class WxAppletServiceImpl implements WxAppletService {
    @Resource
    private RedisUtil redisUtil;

    @Value("${wx.applet.appid}")
    private String appid;

    @Value("${wx.applet.appsecret}")
    private String appSecret;
    @Resource
    private JwtWxConfig jwtConfig;
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private JwtWxConfig jwtWxConfig;

    /*持久层注入*/
    @Autowired
    private IWebMemberService iWebMemberService;




    @Override
    public BaseToken wxUserLogin(MiNiLoginReq req) throws Exception {
        String resultJson = WeiXinUtil.code2Session(appid,appSecret,req.getCode());
        JSONObject json = (JSONObject) JSONObject.parse(resultJson);
        Code2SessionResponse response = JsonUtils.json2Bean(json.get("result").toString(), Code2SessionResponse.class);
        if (!response.getErrcode().equals("0")) {
            throw new AuthenticationException(" : " + response.getErrmsg());
        } else {
            WxAccount wxAccount = new WxAccount();
            wxAccount.setSessionKey(response.getSession_key());
            wxAccount.setWxOpenId(response.getOpenid());
            WebMember webMember = iWebMemberService.getMemberByOpenId(response.getOpenid());
            if (webMember == null) {
                if (StringUtils.isNotEmpty(req.getEncryptedData())) {
                    String info = AesCbcUtil.decrypt(req.getEncryptedData(), response.getSession_key(), req.getVi(), "UTF-8");
                    if (StringUtils.isEmpty(info)) {
                        return new BaseToken("301");
                    }
                    WeiXinUserInfo weiXinUserInfo  = JsonUtils.json2Bean(info,WeiXinUserInfo.class);
                    WebMember wx = new WebMember();
                    wx.setWxOpenId(weiXinUserInfo.getOpenId());
                    wx.setUnionId(weiXinUserInfo.getUnionId());
                    wx.setSessionKey(response.getSession_key());
                    wx.setGender(Integer.valueOf(weiXinUserInfo.getGender()));
                    wx.setCountry(weiXinUserInfo.getCountry());
                    wx.setProvince(weiXinUserInfo.getProvince());
                    wx.setCity(weiXinUserInfo.getCity());
                    wx.setNickName(weiXinUserInfo.getNickName());
                    wx.setAvatarUrl(weiXinUserInfo.getAvatarUrl());
                    iWebMemberService.save(wx);
                }else {
                    return new BaseToken("301");
                }
                webMember = iWebMemberService.getMemberByOpenId(response.getOpenid());
                if (StringUtils.isBlank(webMember.getPhone())) {
                    return new BaseToken("302");
                }

            } else {
                webMember.setSessionKey(response.getSession_key());
                if (StringUtils.isBlank(webMember.getPhone())) {
                    return new BaseToken("302");
                }

                iWebMemberService.updateById(webMember);
            }


            String token = jwtConfig.getWxLoginToken(wxAccount);
            return new BaseToken(token);
        }
    }

    @Override
    public boolean checkToken(String jwtToken, String wxOpenId, String sessionKey) {

        if (!org.springframework.util.StringUtils.hasText(wxOpenId)) {
            throw new UnAuthorizedException("id=null");
        }

        if (!org.springframework.util.StringUtils.hasText(sessionKey)) {
            throw  new UnAuthorizedException("秘钥=null");
        }
        if(!jwtWxConfig.verifyToken(jwtToken)){{
            throw new UnAuthorizedException("token无效");
        }}

        return true;
    }
}
