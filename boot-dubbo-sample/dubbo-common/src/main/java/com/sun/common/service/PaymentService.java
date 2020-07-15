package com.sun.common.service;

import com.sun.common.bean.Payment;
import com.sun.common.vo.ResultVo;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:06
 */
public interface PaymentService {

    ResultVo<List<Payment>> list();

}
