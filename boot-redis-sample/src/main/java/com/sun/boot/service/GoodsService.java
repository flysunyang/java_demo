package com.sun.boot.service;

import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.Goods;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 11:40
 */
public interface GoodsService {

    CommonResult<Integer> addOne(Goods goods);

    CommonResult<Goods> getById(Long id);

    CommonResult<Goods> updateOneById(Goods goods);

    CommonResult<Integer> deleteById(Long id);
}
