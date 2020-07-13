package com.sun.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.boot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-13 10:08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
