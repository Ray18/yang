package com.xi.xlm.wx.shiroConfig;

import com.len.util.JwtToken;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.IWebMemberService;
import com.xi.xlm.wx.entity.WxAccount;
import com.xi.xlm.wx.util.JwtWxConfig;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: WapRealm
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 11:03
 * @Version: 1.0
 **/
@Component
public class WapRealm extends AuthorizingRealm {

    @Autowired
    private JwtWxConfig jwtWxConfig;
    @Autowired
    private IWebMemberService iWebMemberService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @Author YangTianFeng
     * @Date 11:08 2020/7/27
     * @Param [principals]
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @Author YangTianFeng
     * @Date 11:08 2020/7/27
     * @Param [token]
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
         String jwtToken = (String) token.getCredentials();
        String wxOpenId = jwtWxConfig.getWxOpenIdByToken(jwtToken);
        String sessionKey = jwtWxConfig.getSessionKeyByToken(jwtToken);

         WebMember webMember = iWebMemberService.getMemberByOpenId(wxOpenId);

        if (webMember == null) {
            throw new UnknownAccountException("请授权");
        }
        return new SimpleAuthenticationInfo(webMember, token, getName());
    }


}
