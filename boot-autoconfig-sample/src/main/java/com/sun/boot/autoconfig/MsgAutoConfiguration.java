package com.sun.boot.autoconfig;

import com.sun.boot.service.HelloService;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

;import javax.servlet.Servlet;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-16 9:53
 */
@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties({MsgProperties.class})
@Import({HelloService.class})
public class MsgAutoConfiguration {

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnWebApplication
    public static class MyClass {

        public MyClass() {
        }

        @ConditionalOnClass({Tomcat.class})
        @Bean
        public TomcatService tomcat() {
            return new TomcatService("我是tomcat");
        }

        @ConditionalOnClass({Servlet.class})
        @Bean
        public ServletService servlet() {
            return new ServletService("我是servlet");
        }
    }
}
