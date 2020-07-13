package com.sun.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @description:
 * @author: zhaoyang
 * @date: 2020/7/10 14:46
 */
@SpringBootApplication
@EnableCaching
public class BootRedisSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRedisSampleApplication.class, args);
    }

}
