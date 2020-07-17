package com.sun.boot.mapper;

import com.sun.boot.dataobject.UserDO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 10:20
 */
@Repository
public class UserMapper {

    private List<UserDO> userDOList = new ArrayList<>();

    {
        userDOList = Arrays.asList(
                new UserDO(1, "张三", "zhangsan"),
                new UserDO(2, "李四", "lisi"),
                new UserDO(3, "王二", "wanger")
        );
    }

    public UserDO selectById(Integer id) {
        return userDOList.get(id - 1);
    }
}
