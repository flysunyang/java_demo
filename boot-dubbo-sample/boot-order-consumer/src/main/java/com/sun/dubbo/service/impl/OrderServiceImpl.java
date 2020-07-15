package com.sun.dubbo.service.impl;

import com.sun.dubbo.service.OrderService;
import com.sun.dubbo.service.PaymentService;
import com.sun.dubbo.vo.ResultVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 11:20
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * retries: 重试次数
     */
    @DubboReference
    private PaymentService paymentService;

    @Override
    public ResultVo produce() {
        return paymentService.list();
    }

    @Override
    public ResultVo port() {
        return paymentService.port();
    }
}
