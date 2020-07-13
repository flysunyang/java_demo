package com.sun.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @description: String的操作
 * @param:
 * @return:
 * @author: zhaoyang
 * @date: 2020/7/10 17:33
 */
@SpringBootTest
class ListTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void lpush() {
        redisTemplate.opsForList().leftPushAll("list01", "v1", "v2", "v3", "v3");
        List<String> list01 = redisTemplate.opsForList().range("list01", 0, -1);
        list01.stream().forEach(System.out::println);
    }

    @Test
    void del() {
        redisTemplate.opsForList().getOperations().delete("list01");
    }

}
