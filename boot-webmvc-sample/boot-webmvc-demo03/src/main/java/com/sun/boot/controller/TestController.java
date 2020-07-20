package com.sun.boot.controller;

import com.sun.boot.bean.vo.UserVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-20 14:11
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    @CrossOrigin(origins = "*", allowCredentials = "true") // 允许所有来源，允许发送 Cookie
    public UserVO get() {
        return new UserVO().setId(1).setUsername(UUID.randomUUID().toString());
    }

    /**
     * consumes: 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html
     * produces: 指定响应的内容类型（Accept），例如application/json, text/html
     * @param userVO
     * @return
     */
    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserVO add(@RequestBody UserVO userVO) {
        return userVO;
    }

}
