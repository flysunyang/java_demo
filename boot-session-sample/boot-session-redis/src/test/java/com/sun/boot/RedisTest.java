package com.sun.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 9:47
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        stringRedisTemplate.opsForValue().set("k1", "v1");
        String result = stringRedisTemplate.opsForValue().get("k1");
        System.out.println(result);
    }
}
