

package com.xi.xlm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author YangTianFeng
 * @Description  jwt不拦截路径
 * @Date 17:30 2020/9/1
 * @Param 
 * @return 
 **/
@Data
@Component
@ConfigurationProperties(prefix = "team")
public class UrlsProperties {

    /**
     * 不拦截urls
     */
    private List<String> urls;

}
