package com.sun.dubbo.service;

import com.sun.dubbo.bean.Payment;
import com.sun.dubbo.vo.ResultVo;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:06
 */
public interface PaymentService {

    ResultVo<List<Payment>> list();

    ResultVo<String> port();
}
