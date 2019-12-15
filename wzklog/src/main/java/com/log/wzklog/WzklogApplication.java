package com.log.wzklog;

import com.github.jackieonway.sms.annotion.EnabledSmsAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@MapperScan("com.log.wzklog.mapper")
@EnabledSmsAutoConfiguration
@SpringBootApplication
public class WzklogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WzklogApplication.class, args);
    }

}
