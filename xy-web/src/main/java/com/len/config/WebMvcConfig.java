package com.len.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.len.upload.WebUploadProperties;
import com.xi.xlm.common.ueditor.servlet.BaiduUeditorServlet;
import com.xi.xlm.helper.RequestAuthInterceptor;
import com.xi.xlm.properties.UrlsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * @author
 * @date 2018/1/1.
 * @email spring shiro
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Value("${lenosp.uploadPath}")
    private String filePath;
    private RequestAuthInterceptor requestAuthInterceptor;
    private UrlsProperties urlsProperties;

    @Autowired
    private WebUploadProperties webUploadProperties;

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


/*    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(responseBodyConverter());
        super.configureMessageConverters(converters);
    }*/

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 去除掉默认的MappingJackson2HttpMessageConverter，加入自定义的MappingJackson2HttpMessageConverter
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        // 还有种方式：converters.add(0, new MappingJackson2HttpMessageConverter(converterMapper()))
        converters.add(new MappingJackson2HttpMessageConverter(converterMapper()));
        converters.add(responseBodyConverter());
    }

    /**
     * 配置静态访问资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
        registry.addResourceHandler("/plugin/**", "/static/**")
                .addResourceLocations("classpath:/plugin/", "classpath:/static/");
        registry.addResourceHandler("/ftl/**").addResourceLocations("classpath:/ftl/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + filePath + "/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + filePath + "/");
        registry.addResourceHandler("/file/**").addResourceLocations("file:" + filePath + "/");

        super.addResourceHandlers(registry);
    }

    /*保留国际化*/
    @Bean
    public LocaleChangeInterceptor interceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * 拦截过滤器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor());
        //添加自定义拦截器
        registry.addInterceptor(requestAuthInterceptor).addPathPatterns("/w/**").excludePathPatterns(urlsProperties.getUrls());

    }

    @Autowired
    public void setRequestAuthInterceptor(RequestAuthInterceptor requestAuthInterceptor) {
        this.requestAuthInterceptor = requestAuthInterceptor;
    }

    @Autowired
    public void setUrlsProperties(UrlsProperties urlsProperties) {
        this.urlsProperties = urlsProperties;
    }

    @Bean
    public LocaleResolver resolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper converterMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(converterMapper());
        return mappingJackson2HttpMessageConverter;
    }

    /**
     * 注册百度编辑器Servlet
     */
    @Bean
    public ServletRegistrationBean baiduUeditorServlet() {
        String configRootPath = "/plugin/ueditor";
        ServletRegistrationBean registration = new ServletRegistrationBean(new BaiduUeditorServlet());
        registration.addUrlMappings("/ueditor/upload");
        registration.addInitParameter("configRootPath", configRootPath);
        registration.addInitParameter("uploadPath", webUploadProperties.getUploadPath());
        registration.addInitParameter("rootDirName", webUploadProperties.getRootDirName());
        return registration;
    }



    /**
     * @return com.fasterxml.jackson.databind.ObjectMapper
     * @Author YangTianFeng
     * @Description 自定义配置LocalDateTime 统一格式化返回
     * 这里由于是继承了WebMvcConfigurationSupport，所以会导致配置失效
     * 故而使用自定义HttpMessageConverter注入spring管理
     * @Date 11:35 2020/9/5
     * @Param []
     **/
    private ObjectMapper converterMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 不序列化null的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule).registerModule(new ParameterNamesModule());
        objectMapper.setDateFormat(new SimpleDateFormat(Constants.DEFAULT_DATE_TIME_FORMAT));
        return objectMapper;
    }

    static class Constants {
        /**
         * 默认日期时间格式
         */
        public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        /**
         * 默认日期格式
         */
        public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
        /**
         * 默认时间格式
         */
        public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    }
}

