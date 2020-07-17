package com.sun.boot.service;

import com.sun.boot.bo.UserBO;
import com.sun.boot.convert.UserConvert;
import com.sun.boot.dataobject.UserDO;
import com.sun.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 10:26
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserBO getById(Integer id) {
        UserDO userDO = userMapper.selectById(id);
        UserConvert userConvert = UserConvert.instance;
        UserBO userBO = userConvert.convert(userDO);
        return userBO;
    }

}
