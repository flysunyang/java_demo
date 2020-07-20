package com.sun.boot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 14:25
 */
@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 增加 XML 消息转换器
        Jackson2ObjectMapperBuilder xmlBuilder = Jackson2ObjectMapperBuilder.xml();
        xmlBuilder.indentOutput(true);
        converters.add(new MappingJackson2XmlHttpMessageConverter(xmlBuilder.build()));
    }

    /**
     * 跨域访问问题的解决1:
     * 在 Spring Web 中，内置提供 CorsFilter 过滤器，实现对 CORS 的处理
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 创建 UrlBasedCorsConfigurationSource 配置源，类似 CorsRegistry 注册表
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建 CorsConfiguration 配置，相当于 CorsRegistration 注册信息
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有请求来源
        config.setAllowedOrigins(Collections.singletonList("*"));
        // 允许发送 Cookie
        config.setAllowCredentials(true);
        // 允许所有请求 Method
        config.addAllowedMethod("*");
        // 允许所有请求 Header
        config.setAllowedHeaders(Collections.singletonList("*"));
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        config.setExposedHeaders(
                Arrays.asList("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        // 有效期 1800 秒（默认）
        config.setMaxAge(1800L);
        source.registerCorsConfiguration("/**", config);
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(source));
        // 设置 order 排序。这个顺序很重要哦，为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }

    /**
     * 跨域访问问题的解决2:
     * 实现WebMvcConfigurer接口，重写addCorsMappings方法
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 匹配所有 URL ，相当于全局配置
        registry.addMapping("/**")
                // 允许所有请求来源
                .allowedOrigins("*")
                // 允许发送 Cookie
                .allowCredentials(true)
                // 允许所有请求 Method
                .allowedMethods("*")
                // 允许所有请求 Header
                .allowedHeaders("*")
                // 允许所有响应 Header
                .exposedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                // 有效期 1800 秒（默认）
                .maxAge(1800L);
    }

}
