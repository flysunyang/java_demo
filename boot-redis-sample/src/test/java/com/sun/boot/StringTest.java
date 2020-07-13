package com.sun.boot;

import com.sun.boot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: String的操作
 * @param:
 * @return:
 * @author: zhaoyang
 * @date: 2020/7/10 17:33
 */
@SpringBootTest
class StringTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void setNx() {
        // redisTemplate.opsForValue().setIfAbsent("k2", "v2", Duration.ofSeconds(30));
        // 如果存在则设置
        Boolean result = redisTemplate.opsForValue().setIfPresent("k3", "v3");
        System.out.println(result);
    }

    @Test
    void incr() {
        redisTemplate.opsForValue().set("k1", 10);
        Long k1 = redisTemplate.opsForValue().increment("k1");
        System.out.println(k1);
        Long k11 = redisTemplate.opsForValue().increment("k1", 10);
        System.out.println(k11);
    }

    @Test
    void setGet() {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setId(1);
        user.setUsername("zhaoyang");
        user.setAddress("安徽省合肥市");
        map.put("data", user);
        redisTemplate.opsForValue().multiSet(map);
        Object data = redisTemplate.opsForValue().get("data");
        System.out.println(data);
    }



}
