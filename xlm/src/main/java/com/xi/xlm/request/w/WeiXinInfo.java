package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @className: WeiXinInfo
 * @Description:小程序用户信息实体
 * @author:by yangtianfeng
 * @classDate: 2020/9/3 12:28
 * @Version: 1.0
 **/
@Data
@ApiModel("小程序用户信息实体")
public class WeiXinInfo implements Serializable {

    private static final long serialVersionUID = -4425614691897870447L;


    @ApiModelProperty(value = "openid")
    @NotNull(message = "请传入openId")
    private String openId;

    @ApiModelProperty(value = "unionId")
    @NotNull(message = "unionId")
    private String unionId;
    /**
     * 用户昵称
     **/
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    /**
     * 用户性别 0未知 1男 2女
     **/
    @ApiModelProperty(value = "用户性别 0未知 1男 2女")
    private String gender;
    /**
     * 国家
     **/
    @ApiModelProperty(value = "国家")
    private String country;
    /**
     * 省份
     **/
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 城市
     **/
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 用户头像图片
     **/
    @ApiModelProperty(value = "用户头像图片")
    private String avatarUrl;
    /**
     * 语言 en 英文 zh_CN简体中文 zh_TW繁体中文
     **/
    @ApiModelProperty(value = "语言")
    private String language;

}
