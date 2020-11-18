package com.len.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class RConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.maxRetryCount}")
    private int maxRetryCount;

    @Value("${spring.redis.second}")
    private Long second;

    @Value("${spring.redis.password}")
    private String password;



}
