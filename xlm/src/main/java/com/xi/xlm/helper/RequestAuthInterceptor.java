package com.xi.xlm.helper;

import com.xi.xlm.properties.UrlsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @Author YangTianFeng
 * @Description  自定义拦截器 放行URL
 * @Date 17:05 2020/9/1
 * @Param 
 * @return 
 **/
@Component
@Slf4j
public class RequestAuthInterceptor implements HandlerInterceptor {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private UrlsProperties urlsProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        //请求放行，无需验证权限
        if (pathMatcher(url)) {
            return true;
        }
        //当前没有其他验证机制  所以都放行
        return true;
    }

    private boolean pathMatcher(String requestUri) {
        List<String> urls = urlsProperties.getUrls();
        for (String url : urls) {
            if (antPathMatcher.match(url, requestUri)) {
                return true;
            }
        }
        return true;
    }


    @Autowired
    public void setUrlsProperties(UrlsProperties urlsProperties) {
        this.urlsProperties = urlsProperties;
    }

}
