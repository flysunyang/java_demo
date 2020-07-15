package com.sun.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:41
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:provider.xml");
        applicationContext.start();
        System.out.println("服务提供者启动完成!");
        System.in.read();
    }
}
