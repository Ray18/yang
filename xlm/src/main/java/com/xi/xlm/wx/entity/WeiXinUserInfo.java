package com.xi.xlm.wx.entity;

import lombok.Data;

/**
 * @className: WeiXinUserInfo
 * @Description:微信用户信息封装
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 20:08
 * @Version: 1.0
 **/
@Data
public class WeiXinUserInfo {
    private String openId;
    private String unionId;
    /**用户昵称**/
    private String nickName;
    /**用户性别 0未知 1男 2女**/
    private String gender;
    /**国家**/
    private String country;
    /**省份**/
    private String province;
    /**城市**/
    private String city;
    /**用户头像图片**/
    private String avatarUrl;
    /**语言 en 英文 zh_CN简体中文 zh_TW繁体中文**/
    private  String language;

}
