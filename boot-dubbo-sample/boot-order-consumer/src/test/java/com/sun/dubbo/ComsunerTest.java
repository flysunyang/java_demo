package com.sun.dubbo;

import com.sun.dubbo.service.OrderService;
import com.sun.dubbo.vo.ResultVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 11:26
 */
@SpringBootTest
public class ComsunerTest {

    @Autowired
    OrderService orderService;

    @Test
    void consumer() {
        ResultVo resultVo = orderService.produce();
        System.out.println(resultVo);
    }
}
