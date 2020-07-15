package com.sun.dubbo;

import com.sun.dubbo.bean.Payment;
import com.sun.dubbo.service.PaymentService;
import com.sun.dubbo.vo.ResultVo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-14 18:59
 */
public class Consumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:consumer.xml");
        context.start();
        PaymentService paymentService = (PaymentService) context.getBean("paymentService");
        ResultVo<List<Payment>> list = paymentService.list();
        System.out.println(list);
        System.in.read();
    }
}
