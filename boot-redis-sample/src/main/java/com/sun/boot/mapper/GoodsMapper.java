package com.sun.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.boot.autoconfig.MybatisRedisCache;
import com.sun.boot.pojo.Goods;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 11:40
 */
@Mapper
@CacheNamespace(implementation = MybatisRedisCache.class, eviction=MybatisRedisCache.class)
public interface GoodsMapper extends BaseMapper<Goods> {
}
