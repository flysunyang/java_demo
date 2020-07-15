package com.sun.dubbo.mapper;

import com.sun.dubbo.bean.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-15 10:12
 */
@Mapper
public interface PaymentMapper {

    List<Payment> findAll();

}
