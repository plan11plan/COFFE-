package com.sy.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync(proxyTargetClass = true)
@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CafeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeApplication.class, args);
    }

}

