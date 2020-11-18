package com.xi.xlm.wx.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.len.menu.LoginType;
import com.len.util.JwtToken;
import com.xi.xlm.properties.UrlsProperties;
import com.xi.xlm.wx.util.JwtWxConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author:by yangtianfeng
 * @classDate: 2020/7/27 13:00
 * @Version: 1.0
 **/
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private UrlsProperties urlsProperties;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private JwtWxConfig jwtWxConfig;

    @Autowired
    public void setUrlsProperties(UrlsProperties urlsProperties) {
        this.urlsProperties = urlsProperties;
    }

    private boolean pathMatcher(String requestUri) {
        List<String> urls = urlsProperties.getUrls();
        for (String url : urls) {
            if (antPathMatcher.match(url, requestUri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String url = httpRequest.getRequestURI();
        if (pathMatcher(url)) {
            return true;
        }
        String auth = httpRequest.getHeader("Authorization");
        return auth != null && !auth.equals("");

    }

    /**
     * 此方法调用登陆，验证逻辑
     */
    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String auth = httpRequest.getHeader("Authorization");
        String url = httpRequest.getRequestURI();
        if (pathMatcher(url)) {
            return true;
        }

        if (!jwtWxConfig.verifyToken(auth)) {
            responseError(response, "token无效", 403);
            return true;

        }

        String wxOpenId = null;
        String sessionKey = null;
        try {
            wxOpenId = jwtWxConfig.getWxOpenIdByToken(auth);
            sessionKey = jwtWxConfig.getSessionKeyByToken(auth);
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            responseError(response, "错误", 403);
            return true;
        }

        if (!StringUtils.hasText(wxOpenId)) {
            responseError(response, "错误", 403);
            return true;
        }

        if (!StringUtils.hasText(sessionKey)) {
            responseError(response, "错误", 403);
            return true;
        }



        if (isLoginAttempt(request, response)) {
            executeLogin(request, response);
            return true;
        }
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JwtToken jwtToken = new JwtToken(token, LoginType.MINILOGIN);
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
            /***有任何异常则授权失败**/
            e.printStackTrace();
            responseError(response, "请授权",403);
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "访问被拒绝");
        return false;
    }


    /**
     * 提供跨域支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    private void responseError(ServletResponse response, String message, int code) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/w/mini/unauthorized?message=" + message + "&code=" + code);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
