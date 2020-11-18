package com.xi.xlm.wx.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.xi.xlm.exception.UnAuthorizedException;
import com.xi.xlm.wx.entity.WxAccount;
import com.len.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @className: JwtWxConfig
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 13:14
 * @Version: 1.0
 **/
@Component
public class JwtWxConfig {
    private static final String SECRET_KEY = "5371f568a45e5ab1f442c38e0932aef24447139b";

    private static long expire_time = 72000;

    @Autowired
    private RedisUtil redisUtil;



    public String getWxLoginToken(WxAccount wxAccount){
        String jwtId = UUID.randomUUID().toString();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        String token = JWT.create()
                .withClaim("wxOpenId", wxAccount.getWxOpenId())
                .withClaim("sessionKey", wxAccount.getSessionKey())
                .withClaim("jwt-id", jwtId)
                .withExpiresAt(new Date(System.currentTimeMillis() + expire_time*1000))
                .sign(algorithm);
        redisUtil.setEx("JWT-SESSION-" + jwtId, token, expire_time, TimeUnit.SECONDS);
        return token;
    }


    public boolean verifyToken(String token) {
        try {
            String redisToken = redisUtil.get("JWT-SESSION-" + getJwtIdByToken(token));
            if(StringUtils.isEmpty(redisToken)){
                return false;
            }
            if (!redisToken.equals(token)) {
                return false;
            }

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("wxOpenId", getWxOpenIdByToken(redisToken))
                    .withClaim("sessionKey", getSessionKeyByToken(redisToken))
                    .withClaim("jwt-id", getJwtIdByToken(redisToken))
                    .acceptExpiresAt(System.currentTimeMillis() + expire_time*1000 )
                    .build();
            verifier.verify(redisToken);
            redisUtil.setEx("JWT-SESSION-" + getJwtIdByToken(token), redisToken, expire_time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
           return false;
        }
    }



    private String getJwtIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("jwt-id").asString();
    }

    public String getWxOpenIdByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("wxOpenId").asString();
    }

    public String getSessionKeyByToken(String token) throws JWTDecodeException {
        return JWT.decode(token).getClaim("sessionKey").asString();
    }

}
