package com.xi.xlm.wx.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: WxAccount
 * @Description: 微信登录实体（待扩展）
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 12:45
 * @Version: 1.0
 **/
@Data
public class WxAccount implements Serializable {
    private static final long serialVersionUID = -8174501737269377973L;

    private Integer id;
    private String wxOpenId;
    private String sessionKey;
    private Date lastTime;
}
