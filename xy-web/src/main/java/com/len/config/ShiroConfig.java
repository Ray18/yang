package com.len.config;

import com.len.core.filter.PermissionFilter;
import com.len.core.filter.VerfityCodeFilter;
import com.len.core.shiro.LoginRealm;
import com.len.core.shiro.RetryLimitCredentialsMatcher;
import com.len.menu.LoginType;
import com.xi.xlm.properties.UrlsProperties;
import com.xi.xlm.wx.filter.JwtFilter;
import com.xi.xlm.wx.shiroConfig.WapRealm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.*;

/**
 * 2020/4/19 添加redis缓存，支持集群 默认redis缓存，如果单机配置可放开下面 ehcache
 */
@Configuration
public class ShiroConfig {

    RConfig redisConfig;


    /**
     * @return org.apache.shiro.authc.credential.CredentialsMatcher
     * @Author YangTianFeng
     * @Description 密码校验 , 这里因为是JWT形式,就无需密码校验和加密,直接让其返回为true(如果不设置的话,该值默认为false,即始终验证不通过)
     * @Date 13:33 2020/7/27
     * @Param []
     **/
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        return (token, info) -> true;
    }

    @Bean
    public RetryLimitCredentialsMatcher getRetryLimitCredentialsMatcher() {
        RetryLimitCredentialsMatcher rm = new RetryLimitCredentialsMatcher(cacheManager());
        rm.setHashAlgorithmName("md5");
        rm.setHashIterations(4);
        return rm;

    }

    /**
     * 自定义Realm
     **/
    @Bean(name = "userLoginRealm")
    public LoginRealm getLoginRealm() {
        LoginRealm realm = new LoginRealm();
        realm.setCredentialsMatcher(getRetryLimitCredentialsMatcher());
        return realm;
    }

    /**
     * 自定义小程序登录Realm
     **/
    @Bean(name = "wapLoginRealm")
    public WapRealm wapLoginRealm() {
        // 重写 Realm 的 supports() 方法是通过 JWT 进行登录判断的关键
        WapRealm wapRealm = new WapRealm();
        wapRealm.setCredentialsMatcher(credentialsMatcher());

        return wapRealm;
    }


    /*==========ehcache 缓存 begin============*/
    /*@Bean
    public EhCacheManager getCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache.xml");
        return ehCacheManager;
    }*/

    /* @Bean
     public DefaultWebSessionManager defaultWebSessionManager() {
         DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
         defaultWebSessionManager.setSessionIdCookieEnabled(true);
         defaultWebSessionManager.setGlobalSessionTimeout(21600000);
         defaultWebSessionManager.setDeleteInvalidSessions(true);
         defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
         defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
         return defaultWebSessionManager;
     }*/
    /*==========ehcache 缓存 end============*/

    /**
     * 添加注解依赖
     */
    @Bean
    public static  LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AtLeastOneSuccessfulStrategy getAtLeastOneSuccessfulStrategy() {
        return new AtLeastOneSuccessfulStrategy();
    }

    @Bean
    public MyModularRealmAuthenticator getMyModularRealmAuthenticator() {
        MyModularRealmAuthenticator authenticator = new MyModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(getAtLeastOneSuccessfulStrategy());
        return authenticator;
    }


    /**
     * SecurityManager,安全管理器,所有与安全相关的操作都会与之进行交互;
     * 它管理着所有Subject,所有Subject都绑定到SecurityManager,与Subject的所有交互都会委托给SecurityManager
     * DefaultWebSecurityManager :
     * 会创建默认的DefaultSubjectDAO(它又会默认创建DefaultSessionStorageEvaluator)
     * 会默认创建DefaultWebSubjectFactory
     * 会默认创建ModularRealmAuthenticator
     */
    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(@Qualifier("userLoginRealm") LoginRealm loginRealm,
                                              @Qualifier("wapLoginRealm") WapRealm wapLoginRealm
    ) {
        DefaultWebSecurityManager dwm = new DefaultWebSecurityManager();
        List<Realm> loginRealms = new ArrayList<>();
        dwm.setAuthenticator(getMyModularRealmAuthenticator());
        loginRealm.setName(LoginType.SYS.toString());
        loginRealms.add(loginRealm);
        //装入web小程序登录自定义realm列表
        wapLoginRealm.setName(LoginType.MINILOGIN.toString());
        loginRealms.add(wapLoginRealm);

        dwm.setRealms(loginRealms);
        dwm.setCacheManager(cacheManager());
        dwm.setSessionManager(sessionManager());
        return dwm;
    }

    @Bean
    public PermissionFilter getPermissionFilter() {
        return new PermissionFilter();
    }

    /**
     * 注入wap登录过滤器
     **/
    @Bean
    public JwtFilter getWapAuthenticationFilter() {
        return new JwtFilter();
    }

    /**
     * 校验验证码过滤器
     **/
    @Bean
    public VerfityCodeFilter getVerfityCodeFilter() {
        VerfityCodeFilter vf = new VerfityCodeFilter();
        vf.setFailureKeyAttribute("shiroLoginFailure");
        vf.setJcaptchaParam("code");
        vf.setVerfitiCode(true);
        return vf;
    }

    /**
     * 配置Shiro的访问策略
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean sfb = new ShiroFilterFactoryBean();
        sfb.setSecurityManager(securityManager);
        //登录页面
        //sfb.setLoginUrl("/login");
        //设置没有资源权限时跳转页面
        //sfb.setUnauthorizedUrl("/goLogin");

        Map<String, Filter> filters = new HashMap<>();
        filters.put("per", getPermissionFilter());
        filters.put("verCode", getVerfityCodeFilter());

        //加入shrio过滤器列表 wapjwt-前台登录鉴权
        filters.put("wapJwt", getWapAuthenticationFilter());
        sfb.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        //前台鉴权列表 anon-放行
        filterMap.put("/qj/**", "anon");
        filterMap.put("/w/**", "wapJwt");
        filterMap.put("/test/**", "wapJwt");

        //后台鉴权列表
        filterMap.put("/login", "verCode,anon");
        filterMap.put("/main", "per");
        filterMap.put("/getCode", "anon");
        filterMap.put("/actuator/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/file/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/logout", "logout");
        filterMap.put("/plugin/**", "anon");
        filterMap.put("/user/**", "per");
        //放行swagger
        filterMap.put("/swagger-ui.html", "anon");
        //测试放行
        filterMap.put("/m/attractEmployeeInfo/importExcel", "anon");

        sfb.setFilterChainDefinitionMap(filterMap);
        return sfb;
    }


    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     * 开启注解验证
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor as = new AuthorizationAttributeSourceAdvisor();
        as.setSecurityManager(securityManager);
        return as;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");

        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisConfig.getHost() + ":" + redisConfig.getPort());
        redisManager.setPassword(StringUtils.isNotEmpty(redisConfig.getPassword())?redisConfig.getPassword():null);
        redisManager.setTimeout(redisConfig.getTimeout());
        return redisManager;
    }


    @Autowired
    public void setRConfig(RConfig redisConfig) {
        this.redisConfig = redisConfig;
    }



    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }


    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }


}
