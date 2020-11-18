package com.xi.xlm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @className: WxAppProperties
 * @Description: 微信appid
 * @author:by yangtianfeng
 * @classDate: 2020/9/8 14:27
 * @Version: 1.0
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wx.applet")
public class WxAppProperties {
    private String appid;
    private String appsecret;
}
