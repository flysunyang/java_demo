package com.sun.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhaoyang
 * @description 自动化配置 Spring Session 使用 Redis 作为数据源
 * @create 2020-07-21 9:58
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfiguration {

    /**
     * 采用 JSON 序列化方式。因为默认情况下，采用 Java 自带的序列化方式 ，可读性差
     * @return
     */
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }
}
