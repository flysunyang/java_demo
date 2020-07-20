package com.sun.boot.controller;

import com.sun.boot.constants.ServiceExceptionEnum;
import com.sun.boot.exception.ServiceException;
import com.sun.boot.model.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-17 16:30
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    /**
     * 测试抛出 NullPointerException 异常
     */
    @GetMapping("/01")
    public UserVO exception01() {
        throw new NullPointerException("我是空指针异常");
    }

    /**
     * 测试抛出 ServiceException 异常
     */
    @GetMapping("/02")
    public UserVO exception02() {
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }

}
