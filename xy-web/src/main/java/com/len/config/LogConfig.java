package com.len.config;

import com.len.core.annotation.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 
 * @date 2018/1/3.
 * @email 
 */
@Configuration
public class LogConfig {

  @Bean(name = "logAspect")
  public LogAspect getLogAspect(){
    return new LogAspect();
  }

}
