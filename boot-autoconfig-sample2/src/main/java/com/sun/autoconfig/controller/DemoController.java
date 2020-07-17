package com.sun.autoconfig.controller;

import com.sun.boot.autoconfig.MsgProperties;
import com.sun.boot.autoconfig.TomcatService;
import com.sun.boot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 9:59
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    MsgProperties msgProperties;

    @Autowired
    HelloService helloService;

    @Autowired
    TomcatService tomcatService;

    @GetMapping("server")
    public String server() {
        String tomcat = tomcatService.getMsg();
        return tomcat;
    }

    @GetMapping("/msg")
    public Object msg() {
        return msgProperties;
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }
}
