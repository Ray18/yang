package com.xi.xlm.controller.w;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.HttpUtil;
import com.len.util.JsonUtils;
import com.len.util.Result;
import com.xi.xlm.entity.AttractEmployeeInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.request.w.MiNiLoginReq;
import com.xi.xlm.request.w.WeiXinInfo;
import com.xi.xlm.service.IAttractEmployeeInfoService;
import com.xi.xlm.service.IWebMemberService;
import com.xi.xlm.wx.entity.BaseToken;
import com.xi.xlm.wx.entity.WxAccount;
import com.xi.xlm.wx.service.WxAppletService;
import com.xi.xlm.wx.util.AesCbcUtil;
import com.xi.xlm.wx.util.JwtWxConfig;
import com.xi.xlm.wx.util.WeiXinUtil;
import com.xi.xlm.wx.vo.Code2SessionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: MiniLoginController
 * @Description:web小程序登录
 * @author:by yangtianfeng
 * @classDate: 2020/9/1 16:37
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/mini")
@Slf4j
public class MiniLoginController {
    @Autowired
    private WxAppletService wxAppletService;
    @Autowired
    private IWebMemberService iWebMemberService;

    @Autowired
    private IAttractEmployeeInfoService iAttractEmployeeInfoService;

    @Value("${wx.applet.appid}")
    private String appid;

    @Value("${wx.applet.appsecret}")
    private String appSecret;

    @Resource
    private JwtWxConfig jwtConfig;

    @PostMapping("miniLogin")
    public Result miniLogin(@RequestBody MiNiLoginReq req) {
        try {
            BaseToken baseToken = wxAppletService.wxUserLogin(req);
            if (baseToken.getToken().equals("301")) {
                return Result.error(301, "需要授权");
            }
            if (baseToken.getToken().equals("302")) {
                return Result.error(302, "需要授权手机号");
            }

            return Result.ok(baseToken);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("updateMiniInfo")
    public Result updateMiniInfo(@RequestBody WeiXinInfo info) {
        WebMember webMember = iWebMemberService.getMemberByOpenId(info.getOpenId());
        if (webMember != null) {
            webMember.setNickName(info.getNickName());
            webMember.setGender(Integer.valueOf(info.getGender()));
            webMember.setCountry(info.getCountry());
            webMember.setProvince(info.getProvince());
            webMember.setCity(info.getCity());
            webMember.setAvatarUrl(info.getAvatarUrl());

        }
        return Result.ok();
    }


    @PostMapping("updateTel")
    public Result updateTel(@RequestBody MiNiLoginReq req) throws Exception {
        String resultJson = WeiXinUtil.code2Session(appid,appSecret,req.getCode());
        JSONObject json = (JSONObject) JSONObject.parse(resultJson);
        Code2SessionResponse response = JsonUtils.json2Bean(json.get("result").toString(), Code2SessionResponse.class);

        WebMember webMember = iWebMemberService.getMemberByOpenId(response.getOpenid());
        if (webMember != null) {
            String info = AesCbcUtil.decrypt(req.getEncryptedData(), response.getSession_key(), req.getVi(), "UTF-8");
            log.info(info);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(info);
            webMember.setPhone(jsonObject.get("phoneNumber").toString());
            QueryWrapper<AttractEmployeeInfo> employeeInfoQueryWrapper = new QueryWrapper<>();
            employeeInfoQueryWrapper.like(AttractEmployeeInfo.PHONE, webMember.getPhone());
            if (iAttractEmployeeInfoService.getOne(employeeInfoQueryWrapper) != null) {
                webMember.setAttractFlag(true);
            }


            iWebMemberService.updateById(webMember);
            WxAccount wxAccount = new WxAccount();
            wxAccount.setSessionKey(response.getSession_key());
            wxAccount.setWxOpenId(response.getOpenid());
            String token = jwtConfig.getWxLoginToken(wxAccount);
            return Result.ok(token);
        } else {
            return Result.error(301, "需要授权");
        }
    }





    @RequestMapping(path = "unauthorized")
    public Result unauthorized(String message,int code) throws UnsupportedEncodingException {
        return Result.error(code, message, "");
    }

}
