package com.xi.xlm.controller.w;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.collect.Maps;
import com.len.base.CurrentUser;
import com.len.redis.RedisUtil;
import com.len.util.HttpUtil;
import com.len.util.JsonUtil;
import com.len.util.JsonUtils;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.exception.UnAuthorizedException;
import com.xi.xlm.request.w.TakeAddressReq;
import com.xi.xlm.wx.entity.WxAccount;
import com.xi.xlm.wx.util.WeiXinUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @className: TestC
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/7/30 10:36
 * @Version: 1.0
 **/
@RestController
@RequestMapping("test")
@Slf4j
public class TestC {
    @Autowired
    private RedisUtil redisUtil;


    /**
     * JWT 自定义密钥 可配置到资源文件去
     */
    private static final String SECRET_KEY = "5371f568a45e5ab1f442c38e0932aef24447139b";

    /**
     * @Author YangTianFeng
     * @Description  小程序模拟登陆
     * @Date 13:13 2020/8/28
     * @Param []
     * @return com.len.util.JsonUtil
     **/
    @GetMapping("miniLogin")
    public JsonUtil miniLogin(){
        //JWT 随机ID,做为验证的key
        String jwtId = UUID.randomUUID().toString();
        //1 . 加密算法进行签名得到token
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                //token里面装入openid
                .withClaim("wxOpenId", "oWQ6w4sKjrMawvRo7y_nOcbdXLnU")
                //token里面装入秘钥
                .withClaim("sessionKey","1234567891")
                //装入验证Key
                .withClaim("jwt-id", jwtId)
                //JWT 配置过期时间的正确姿势
                .withExpiresAt(new Date(System.currentTimeMillis() + 7200*1000))
                .sign(algorithm);
        //2 . Redis缓存JWT, 注 : 请和JWT过期时间一致
        redisUtil.setEx("JWT-SESSION-" + jwtId, token, 7200, TimeUnit.SECONDS);
        return JsonUtil.sucess(token);
    }


    @GetMapping("test")
    public JsonUtil test(){
        WebMember webMember = UPrincipal.getMember();
        log.debug("ssssss");
        JsonUtil jsonUtil  = new JsonUtil();
        jsonUtil.setMsg("访问成功");
        jsonUtil.setData(new Date());
        return jsonUtil;
    }



    @PostMapping("postTestN")
    public String  postTestN(@Valid @RequestBody TakeAddressReq jsons){

        log.info(jsons.getId());
        return jsons.getId();
    }

    @PostMapping("postTest")
    public String  postTest(@RequestBody String jsons){
        Map<String,String > map = new HashMap<>();
        JSONObject jsonObject  = (JSONObject) JSONObject.parse(jsons);
        map.put("msg", jsonObject.get("jsons").toString());
        map.put("code", "200");
        log.info(jsons);
        return JsonUtils.map2Json(map);
    }

    public static void main(String[] args) {
      String  a =   WeiXinUtil.getAccessToken("wx2180bee8b0490aab", "e9dd8ba920eddb5f5ed4ede358bdad5a");
     // log.info(a);
      //  JSONObject resultObj  = JSONObject.parseObject(a);
        WeiXinUtil.getLiveInfo(a,0,10);
    }



}

