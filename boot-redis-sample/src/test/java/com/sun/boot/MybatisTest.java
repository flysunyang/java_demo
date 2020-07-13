package com.sun.boot;

import com.sun.boot.autoconfig.ApplicationContextRegister;
import com.sun.boot.mapper.UserMapper;
import com.sun.boot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:09
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testIOC() {
        RedisTemplate<String, String> redisTemplate = ApplicationContextRegister.getBean("redisTemplate");
        System.out.println(redisTemplate);
    }

    @Test
    void testSelect() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    void testInert() {
        User user = new User();
        user.setUsername("朝阳");
        user.setPassword("zhaoyang");
        user.setAddress("安徽省合肥市");
        userMapper.insert(user);
    }
}
