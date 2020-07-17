package com.sun.boot.service;

import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 10:02
 */
@Configuration
public class HelloService {

    public String hello() {
        return "我是boot-autoconfig-sample模块HelloService的hello方法";
    }
}
