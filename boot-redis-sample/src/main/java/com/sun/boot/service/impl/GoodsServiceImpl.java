package com.sun.boot.service.impl;

import com.sun.boot.mapper.GoodsMapper;
import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.Goods;
import com.sun.boot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 11:41
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public CommonResult<Integer> addOne(Goods goods) {
        int result = goodsMapper.insert(goods);
        Assert.state(result > 0, "新增失败，请联系管理员~");
        return new CommonResult<>(200, "success", result);
    }

    @Override
    public CommonResult<Goods> getById(Long id) {
        Goods goods = goodsMapper.selectById(id);
        Assert.notNull(goods, "根据ID：" + id + "查询不到数据");
        return new CommonResult<>(200, "success", goods);
    }

    @Override
    public CommonResult<Goods> updateOneById(Goods goods) {
        int result = goodsMapper.updateById(goods);
        Assert.state(result > 0, "更新失败，请联系管理员~");
        Goods updateGoods = goodsMapper.selectById(goods.getId());
        return new CommonResult<>(200, "success", updateGoods);
    }

    @Override
    public CommonResult<Integer> deleteById(Long id) {
        int result = goodsMapper.deleteById(id);
        Assert.state(result > 0, "删除失败，请联系管理员~");
        return new CommonResult<>(200, "success", result);
    }
}
