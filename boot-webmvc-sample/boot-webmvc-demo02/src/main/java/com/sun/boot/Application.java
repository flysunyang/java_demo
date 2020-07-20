package com.sun.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 15:27
 */
@ServletComponentScan(basePackages = {"com.sun.boot.servlet"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
