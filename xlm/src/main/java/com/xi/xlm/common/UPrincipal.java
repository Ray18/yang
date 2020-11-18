package com.xi.xlm.common;

import com.xi.xlm.entity.WebMember;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @className: UPrincipal
 * @Description: 获取登s录用s户信s息
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 15:11
 * @Version: 1.0
 **/
public class UPrincipal {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    public static Session getSession() {
        return getSubject().getSession();
    }



    public static WebMember getMember() {
        WebMember webMember = (WebMember) SecurityUtils.getSubject().getPrincipal();
        return webMember;
    }

}
