package com.xi.xlm.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wxpay")
public class WxPayProperties {
    /**
     * appSecret 是 appId 对应的接口密码，微信公众号授权获取用户 openId 时使用
     */
    private String appSecret;
    /**
     * apiclient_cert.p1 证书路径，在微信商户后台下载
     */
    private String certPath;
    /**
     * 外网访问项目的域名，支付通知中会使用
     */
    private String domain;
    /**
     * 微信支付商户号
     */
    private String mchId;
    /**
     * API 密钥
     */
    private String partnerKey;

    /**
     * 应用编号
     */
    private String appId;

}
