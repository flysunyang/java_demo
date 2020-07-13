package com.sun.boot.controller;

import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.User;
import com.sun.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:32
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        try {
            return userService.deleteById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }

    @PutMapping("/update")
    public CommonResult update(User user) {
        try {
            return userService.updateOneById(user);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }

    @PostMapping("/add")
    public CommonResult add(User user) {
        try {
            return userService.addOne(user);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }
    
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        try {
            return userService.getById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }
}
