package com.xi.xlm.wx.entity;

import lombok.Data;

/**
 * @className: BaseToken
 * @Description: 微信登录公共token
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 12:52
 * @Version: 1.0
 **/
@Data
public class BaseToken {
    private String token;

    public BaseToken(String token){
        this.token = token;
    }
}
