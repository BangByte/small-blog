package com.log.wzklog.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ApplicationStart implements ApplicationRunner {

    public static LocalDateTime  startTime = null;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        startTime = LocalDateTime.now();
    }
}
