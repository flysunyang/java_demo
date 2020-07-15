package com.sun.dubbo.service.impl;

import com.sun.dubbo.bean.Payment;
import com.sun.dubbo.mapper.PaymentMapper;
import com.sun.dubbo.service.PaymentService;
import com.sun.dubbo.vo.ResultVo;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 10:19
 */
@Service
@DubboService(interfaceClass = PaymentService.class, retries = 3, timeout = 2000, loadbalance = "random")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    ProtocolConfig protocolConfig;

    @Override
    public ResultVo<List<Payment>> list() {
        List<Payment> paymentList = paymentMapper.findAll();
        return ResultVo.success(paymentList);
    }

    @Override
    public ResultVo<String> port() {
        System.out.println("=========port请求了");
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return ResultVo.success(protocolConfig.getPort());
    }


}
