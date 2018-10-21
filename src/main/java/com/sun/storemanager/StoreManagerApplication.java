package com.sun.storemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//启用JPA审计
@EnableJpaAuditing
//启用缓存
@EnableCaching
//启用异步
//@EnableAsync
public class StoreManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagerApplication.class, args);
    }
}
