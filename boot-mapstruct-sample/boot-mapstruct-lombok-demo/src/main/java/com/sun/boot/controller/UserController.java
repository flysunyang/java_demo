package com.sun.boot.controller;

import com.sun.boot.bo.UserBO;
import com.sun.boot.convert.UserConvert;
import com.sun.boot.dataobject.UserDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 10:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public UserBO hello() {
        UserDO userDO = new UserDO(1, "张三", "zhangsan");
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        return userBO;
    }

}
