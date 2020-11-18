package com.len;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication(scanBasePackages = "com.xi.xlm.exception")
@EnableTransactionManagement
@ComponentScan({"com.len", "com.xi", "com.xi.xlm.exception"})
@MapperScan(basePackages = {"com.len.mapper", "com.xi.xlm.mapper"})
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})
public class LenApplication {

    public static void main(String[] args) {
        SpringApplication.run(LenApplication.class, args);
    }


}
