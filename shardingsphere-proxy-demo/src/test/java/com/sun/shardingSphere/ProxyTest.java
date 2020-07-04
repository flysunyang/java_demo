package com.sun.shardingSphere;

import com.sun.shardingsphere.bean.User;
import com.sun.shardingsphere.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-03 18:13
 */
@SpringBootTest
public class ProxyTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testQuery() {
        User user = userMapper.selectById(1279325304099188738L);
        System.out.println(user);
    }

    @Test
    void testAdd() {
        User user = new User();
        user.setUsername("田七");
        user.setPassword("tianqi");
        user.setRoleId(15);
        int result = userMapper.insert(user);
        System.out.println(result);
    }
}
