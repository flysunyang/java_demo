package com.sun.dubbo.controller;

import com.sun.dubbo.service.OrderService;
import com.sun.dubbo.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 11:32
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ResultVo list() {
        return orderService.produce();
    }

    @GetMapping("/port")
    public ResultVo port() {
        return orderService.port();
    }
}
