package com.sun.dubbo.service.impl;


import com.sun.dubbo.bean.Payment;
import com.sun.dubbo.service.PaymentService;
import com.sun.dubbo.vo.ResultVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:20
 */
public class PaymentServiceImpl implements PaymentService {

    private List<Payment> paymentList = new ArrayList<>();

    {
        paymentList = Arrays.asList(
                new Payment(1L, "支付单1", 123.4),
                new Payment(2L, "支付单2", 234.5)
        );
    }

    @Override
    public ResultVo<List<Payment>> list() {
        return ResultVo.success(paymentList);
    }
}
