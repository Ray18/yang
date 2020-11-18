package com.xi.xlm.wx.service;

import com.xi.xlm.request.w.MiNiLoginReq;
import com.xi.xlm.wx.entity.BaseToken;

/**
 * @className: WxAppletService
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 12:50
 * @Version: 1.0
 **/
public interface  WxAppletService {

    public BaseToken wxUserLogin(MiNiLoginReq req) throws Exception;


    boolean checkToken ( String jwtToken, String wxOpenId, String sessionKey);
}
