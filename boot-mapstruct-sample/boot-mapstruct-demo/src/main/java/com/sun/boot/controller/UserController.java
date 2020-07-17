package com.sun.boot.controller;

import com.sun.boot.bo.UserBO;
import com.sun.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 9:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public UserBO get(@PathVariable("id") Integer id) {
        UserBO userBO = userService.getById(id);
        return userBO;
    }

}
