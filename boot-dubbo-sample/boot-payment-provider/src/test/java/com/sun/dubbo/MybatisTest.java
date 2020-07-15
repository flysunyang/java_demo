package com.sun.dubbo;

import com.sun.dubbo.bean.Payment;
import com.sun.dubbo.service.PaymentService;
import com.sun.dubbo.vo.ResultVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 10:44
 */
@SpringBootTest
public class MybatisTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void list() {
        ResultVo<List<Payment>> list = paymentService.list();
        System.out.println(list);
    }
}
