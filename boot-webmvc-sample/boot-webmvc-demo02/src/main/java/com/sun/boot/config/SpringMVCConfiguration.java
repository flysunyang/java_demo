package com.sun.boot.config;

import com.sun.boot.interceptor.FirstInterceptor;
import com.sun.boot.interceptor.SecondInterceptor;
import com.sun.boot.interceptor.ThirdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 17:04
 */
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    @Bean
    public FirstInterceptor firstInterceptor() {
        return new FirstInterceptor();
    }

    @Bean
    public SecondInterceptor secondInterceptor() {
        return new SecondInterceptor();
    }

    @Bean
    public ThirdInterceptor thirdInterceptor() {
        return new ThirdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器一
        registry.addInterceptor(this.firstInterceptor()).addPathPatterns("/**");
        // 拦截器二
        registry.addInterceptor(this.secondInterceptor()).addPathPatterns("/users/current_user");
        // 拦截器三
        registry.addInterceptor(this.thirdInterceptor()).addPathPatterns("/**");
    }
}
