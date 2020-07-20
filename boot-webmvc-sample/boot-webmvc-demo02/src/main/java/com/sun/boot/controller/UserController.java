package com.sun.boot.controller;

import com.sun.boot.constants.ServiceExceptionEnum;
import com.sun.boot.exception.ServiceException;
import com.sun.boot.model.vo.CommonResult;
import com.sun.boot.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 15:27
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping("/exception-03")
    public void exception03() {
        log.info("[exception03]");
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }

    @GetMapping("/current_user")
    public UserVO currentUser() {
        log.info("[currentUser]");
        return new UserVO().setId(10).setUsername(UUID.randomUUID().toString());
    }

    @GetMapping("/do_something2")
    public CommonResult<String> doSomething2() {
        return CommonResult.success("success");
    }

    @GetMapping("/do_something")
    @ResponseBody
    public Object doSomething() {
        return "success";
    }

    @GetMapping("/get")
    public UserVO get(@RequestParam("id") Integer id) {
        return new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
    }

    @GetMapping("/get2")
    public CommonResult<UserVO> get2(@RequestParam("id") Integer id) {
        UserVO user = new UserVO().setId(id).setUsername(UUID.randomUUID().toString());
        int i = 1 / 0;
        return CommonResult.success(user);
    }
}