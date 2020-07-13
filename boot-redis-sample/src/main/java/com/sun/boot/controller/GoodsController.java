package com.sun.boot.controller;

import com.sun.boot.pojo.CommonResult;
import com.sun.boot.pojo.Goods;
import com.sun.boot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:32
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    
    @Autowired
    private GoodsService goodsService;

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        try {
            return goodsService.deleteById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }

    @PutMapping("/update")
    public CommonResult update(Goods goods) {
        try {
            return goodsService.updateOneById(goods);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }

    @PostMapping("/add")
    public CommonResult add(Goods goods) {
        try {
            return goodsService.addOne(goods);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }
    
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        try {
            return goodsService.getById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return new CommonResult(500, "fail", e.getLocalizedMessage());
        }
    }
}
