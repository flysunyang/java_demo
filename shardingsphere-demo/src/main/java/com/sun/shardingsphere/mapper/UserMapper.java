package com.sun.shardingsphere.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.shardingsphere.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-02 16:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
